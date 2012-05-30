$(document).ready(function(){

	//alert('test');

	$(".tio").click(openWindow);

	});

function openWindow(e) { 
	e.preventDefault();
	
	var link = $(this);
	var url = link.attr('href'); 
	
	//alert(url);
/*
	if ($.browser.msie) {

	window.location = url;

	} else {

	window.open(url);

	}
*/

	//window.open('/public/images/glassA.jpg', 
	//		'TRY IT ON POPUP', 'top=50,left=500,width=400px,height=500px,location=no,menubar=no,toolbar=no,scrollbars=yes');
	window.open("tio?path="+url,'','top=50,left=500,width=500px,height=500px,location=no,menubar=no,toolbar=no,scrollbars=yes');
	}


function openGallery(e){
	e.preventDefault();
	window.open("viewSavedPhotos",'','top=50,left=500,width=500px,height=500px,location=no,menubar=no,toolbar=no,scrollbars=yes');
}