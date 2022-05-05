$(() => {
	$('a.trinti').click(deleteHandler);
	$('a#logout_link').click(logoutHandler);
	$('#login_form input[name="username"]').focus();
});

function deleteHandler() {
	$.post($(this).attr('href'), { id: $(this).data('id'), _csrf: $(this).data('token') }).
		done(() => { $(this).parents('.irasas').remove() });
	return false;
}

function logoutHandler() {
	$.post($(this).attr('href'), { _csrf: $(this).data('token') }).
		done((result) => { document.write(result); document.close(); });
	return false;
}