$(() => {
	$('a.trinti').click(deleteHandler);
	$('a#logout_link').click(logoutHandler);
	$('#login_form input[name="username"]').focus();
	$('button.edit').click(editHandler);
});

function editHandler() {
	let path = $(this).data('path');
	$.get(path).done((response) => {
		$('#edit_modal .modal-body').html(response);
	});
	let modal = new bootstrap.Modal($('#edit_modal').get(0));
	modal.show();
	return false;
}

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