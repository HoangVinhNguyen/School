/* global bootstrap: false */

//Click event handler for nav-items
$(document).ready(function() {
	//checkedNavItem();
});

function checkedNavItem() {
	$('.nav-link').each(function() {
		if (window.location.href.search($(this).attr("href")) !== -1) {
			$(this).removeClass("active");
			$(this).addClass("active");
		}
		else {
			$(this).removeClass("active");
		}
	});
};


