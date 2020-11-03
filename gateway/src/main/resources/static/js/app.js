function accessStrategy(url, accessAllowedBehaviour, accessDeniedBehaviour) {
    let token = localStorage.getItem("access_token");

    if (!token) {
        accessDeniedBehaviour();
    } else {
        tokenStrategy(url, token, accessAllowedBehaviour, accessDeniedBehaviour);
    }
}

function tokenStrategy(url, token, accessAllowedBehaviour, accessDeniedBehaviour) {
    $.ajax({
        url: url,
        type: "get",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + token);
        },
        success: function(user) {
            accessAllowedBehaviour(user);
        },
        error: function(response) {
            // expired
            if(response.status === 401 && response.status === 404) {
                localStorage.clear();
                accessDeniedBehaviour();
            }
            // TODO redirect to error page?
        }
    });
}

function toggle(selector) {
    let element = $(selector);

    element.click(function(){
        if (element.hasClass('open')) {
            element.removeClass('open')
        } else {
            element.addClass('open');
        }
    });
}

