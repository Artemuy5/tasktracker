var baseUrl = $('#baseUrl').attr('content');

function leaveComment(taskId) {
    $('#comment-' + taskId).removeClass('hidden');
    $('#comment-' + taskId).addClass('show');
}

function sendComment(taskId) {
    var text = $('#comment-' + taskId + ' > textarea').val();
    $.ajax({
        url: baseUrl + 'comment/add',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            taskId: taskId,
            text: text
        })
    }).done(function() {
        location.reload();
    });
}

function cancelComment(taskId) {
    $('#comment-' + taskId).removeClass('show');
    $('#comment-' + taskId).addClass('hidden');
}


