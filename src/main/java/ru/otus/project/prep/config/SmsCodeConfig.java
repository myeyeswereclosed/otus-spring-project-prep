package ru.otus.project.prep.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.*;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.otus.project.prep.service.sms.code_generation.availability.CodeGenerationAvailability;
import ru.otus.project.prep.service.sms.code_generation.availability.PhoneCodeGenerationAvailabilityChecker;
import ru.otus.project.prep.service.sms.code_generation.service.PhoneCodeService;
import ru.otus.project.prep.service.sms.mobile_provider.MobileProvider;

@Configuration
@RequiredArgsConstructor
public class SmsCodeConfig {
    private static final String REQUEST_CHANNEL = "requestChannel";
    private static final String ROUTING_CHANNEL = "routingChannel";
    private static final String RESPONSE_CHANNEL = "responseChannel";
    private static final String GENERATE_CODE_CHANNEL = "generationChannel";
    private static final String SEND_SMS_CHANNEL = "sendSmsChannel";

    private final PhoneCodeGenerationAvailabilityChecker validator;
    private final PhoneCodeService service;
    private final MobileProvider mobileProvider;

    @Bean (name = PollerMetadata.DEFAULT_POLLER )
    public PollerMetadata poller () {
        return
            Pollers
                .fixedRate(100)
                .maxMessagesPerPoll(5)
                .get();
    }

    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();

        pool.setCorePoolSize(2);
        pool.setMaxPoolSize(2);
        pool.setWaitForTasksToCompleteOnShutdown(true);

        return pool;
    }

    @Bean
    public MessageChannel requestChannel() {
        return MessageChannels.queue(100).get();
    }

    @Bean
    public MessageChannel responseChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public MessageChannel routingChannel() {
        return MessageChannels.publishSubscribe(ROUTING_CHANNEL, executor()).get();
    }

    @Bean
    public IntegrationFlow flow() {
        return
            IntegrationFlows
                .from(REQUEST_CHANNEL)
                .handle((GenericHandler<String>) (phone, headers) -> validator.run(phone))
                .channel(ROUTING_CHANNEL)
                .get()
        ;
    }

    @ServiceActivator(inputChannel = ROUTING_CHANNEL)
    @Bean
    public RecipientListRouter router() {
        RecipientListRouter router = new RecipientListRouter();
        router.setIgnoreSendFailures(false);
//        router.setApplySequence(true);
        router.addRecipient(RESPONSE_CHANNEL);
        router.addRecipient(GENERATE_CODE_CHANNEL);

        return router;
    }

    @Bean
    public IntegrationFlow newCodeFlow() {
        return
            IntegrationFlows
                .from(GENERATE_CODE_CHANNEL)
                .transform(CodeGenerationAvailability.class, CodeGenerationAvailability::getPhone)
                .handle((GenericHandler<String>) (phone, headers) -> service.makeCode(phone))
                .channel(SEND_SMS_CHANNEL)
                .get()
        ;
    }

    @Bean
    public IntegrationFlow mobileProviderFlow() {
        return
            IntegrationFlows
                .from(SEND_SMS_CHANNEL)
                .handle("mobileProviderStub", "send")
                .get()
        ;
    }
}
