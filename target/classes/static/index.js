$(() => {
	$('a.trinti').click(deleteHandler);
	$('a#logout_link').click(logoutHandler);
});

function deleteHandler() {
	$.post($(this).attr('href'), { id: $(this).attr('data-id') }).
		done(() => { $(this).parents('.irasas').remove() });
	return false;
}

function logoutHandler() {
	$.post($(this).attr('href'), { _csrf: $(this).data('token') }).
		done((result) => { document.write(result); document.close(); });
	return false;
}