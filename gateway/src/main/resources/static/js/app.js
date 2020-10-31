function accessStrategy(accessAllowedBehaviour, accessDeniedBehaviour) {
    let token = localStorage.getItem("access_token");

    if (!token) {
        accessDeniedBehaviour();
    } else {
        tokenStrategy(token, accessAllowedBehaviour, accessDeniedBehaviour);
    }
}

// ololo
function tokenStrategy(token, accessAllowedBehaviour, accessDeniedBehaviour) {
    $.ajax({
        url: "/checkAccess",
        type: "get",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + token);
        },
        success: function(user) {
            accessAllowedBehaviour(user);
        },
        error: function(response) {
            // expired
            if(response.status === 401) {
                localStorage.clear();
                accessDeniedBehaviour();
            }
            // TODO redirect to error page?
        }
    });
}

