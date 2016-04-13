$(function(){
	$(".subscribe-to-tag").on('click', toogleUserSubscrb);

	function toogleUserSubscrb(event){
		event.preventDefault();
		var post = $(this);

		$.ajax({
			url: post.attr("data-url"),
			method: "POST",
			success: function() {
				changeText(post.html());
			},
			error: function(jqXHR) {
				errorPopup(Messages.get('error.occured'), post, "center-popup");
				console.log(jqXHR);
			}
		});

		function changeText(text){
			text == Messages.get('tag_page.unsubscribe') ? post.html(Messages.get('tag_page.subscribe')) : post.html(Messages.get('tag_page.unsubscribe'));
		}
	}

});