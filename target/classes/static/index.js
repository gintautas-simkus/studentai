$(() => {
	$('a.trinti').click(deleteHandler);
});

function deleteHandler() {
	$.post($(this).attr('href'), { id: $(this).attr('data-id') }).
		done(() => { $(this).parents('.irasas').remove() });
	return false;
}