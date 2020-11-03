package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.otus.project.gateway.config.Info;
import ru.otus.project.gateway.config.PathConfig;

@Component
@RequiredArgsConstructor
public class ModelBuilder {
    private final PathConfig pathConfig;
    private final Info info;

    public Model build(Model model) {
        return
            model
                .addAttribute("path", pathConfig.getContextPath())
                .addAttribute("baseName", info.getName())
                .addAttribute("baseAddress", info.getAddress())
                .addAttribute("basePhone", info.getPhone())
        ;
    }
}
