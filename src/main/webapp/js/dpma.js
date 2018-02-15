// // ##### Carousel in Bühne ####################################################

// $(document).ready(function() {
	// var carousel_is_paused = false;
	// $('#dpma-carousel').carousel({
		// interval: 5000 /* 50000 */
	// });


	// /*
	// //Carousel bei Start anhalten
	// $('#dpma-carousel').carousel('pause');
	// carousel_is_paused = true;
	// $('#carousel-pause-button span').removeClass('glyphicon-pause');
	// $('#carousel-pause-button span').addClass('glyphicon-play');
	// */

	// //Pause-Button nur anzeigen, wenn JS aktiv
	// $('#dpma-carousel-controls').show();

	// /*
	// $('.list-group-item').on('click', function() {
		// $('#dpma-carousel div.item.active h3 a').focus();
	// });
	// */

	// $('#carousel-pause-button').on('click', function() {
		// if(carousel_is_paused)
		// {
			// $('#dpma-carousel').carousel('cycle');
			// $('#carousel-pause-button span').removeClass('glyphicon-play');
			// $('#carousel-pause-button span').addClass('glyphicon-pause');
		// }
		// else
		// {
			// $('#dpma-carousel').carousel('pause');
			// $('#carousel-pause-button span').removeClass('glyphicon-pause');
			// $('#carousel-pause-button span').addClass('glyphicon-play');

		// }
		// carousel_is_paused = !carousel_is_paused;
	// });

	// $('#dpma-carousel').on('slid.bs.carousel', function () {
		// var curSlide = $('#dpma-carousel div.carousel-inner div.active').index();
		// var myCarouselNav = $('#dpma-carousel-nav');
		// myCarouselNav.find('a.list-group-item.active').removeClass('active');
		// var $nextIndicator = myCarouselNav.find('a.list-group-item').eq(curSlide);
		// $nextIndicator && $nextIndicator.addClass('active');
	// });

	// /*
	// $('#dpma-carousel').on('slid.bs.carousel', function () {
				// var curSlide = $('#dpma-carousel div.carousel-inner div.active').index();
				// var myCarouselNav = $('#dpma-carousel-nav div');
				// myCarouselNav.find('.active').removeClass('active');
				// var $nextIndicator = $(myCarouselNav.children()[curSlide]);
				// $nextIndicator && $nextIndicator.addClass('active');
	// });
	// */
// });
// // ############################################################################






// ##### Carousel in Bühne ####################################################

$(document).ready(function() {
	var carousel_is_paused = false;
	$('#dpma-carousel').carousel({
		interval: 5000 /* 50000 */
	});


	/*
	//Carousel bei Start anhalten
	$('#dpma-carousel').carousel('pause');
	carousel_is_paused = true;
	$('#carousel-pause-button span').removeClass('glyphicon-pause');
	$('#carousel-pause-button span').addClass('glyphicon-play');
	*/

	//Pause-Button nur anzeigen, wenn JS aktiv
	$('#dpma-carousel-controls').show();

	/*
	$('.list-group-item').on('click', function() {
		$('#dpma-carousel div.item.active h3 a').focus();
	});
	*/

	$('#carousel-pause-button').on('click', function() {
		if(carousel_is_paused)
		{
			$('#dpma-carousel').carousel('cycle');
			$('#carousel-pause-button span').removeClass('glyphicon-play');
			$('#carousel-pause-button span').addClass('glyphicon-pause');
		}
		else
		{
			$('#dpma-carousel').carousel('pause');
			$('#carousel-pause-button span').removeClass('glyphicon-pause');
			$('#carousel-pause-button span').addClass('glyphicon-play');

		}
		carousel_is_paused = !carousel_is_paused;
	});

	$('#dpma-carousel').on('slid.bs.carousel', function () {
		var curSlide = $('#dpma-carousel div.carousel-inner div.active').index();
		//console.log("Aktuelle Folie: " + curSlide);
		var myCarouselNav = $('#dpma-carousel-nav');
		myCarouselNav.find('.row > div.active').removeClass('active');
		var $nextIndicator = myCarouselNav.find('.row > div').eq(curSlide);
		$nextIndicator && $nextIndicator.addClass('active');
	});
	
	$('#dpma-carousel-nav a').click(function(evt) {
		var i = $(this).parent().index();		
		var curSlide = $('#dpma-carousel div.carousel-inner div.active').index();
		
		if (curSlide !== i)
		{
			evt.preventDefault(); //Link nicht folgen, nur Karussell navigieren
			$('#dpma-carousel').carousel(i);
		}
	});

	/*
	$('#dpma-carousel').on('slid.bs.carousel', function () {
				var curSlide = $('#dpma-carousel div.carousel-inner div.active').index();
				var myCarouselNav = $('#dpma-carousel-nav div');
				myCarouselNav.find('.active').removeClass('active');
				var $nextIndicator = $(myCarouselNav.children()[curSlide]);
				$nextIndicator && $nextIndicator.addClass('active');
	});
	*/
	
	var width_is_mobile = (document.body.clientWidth < 768);
	
	//TODO: mehrfaches ausfuehren verhindern
	var stopCarouselOnMobile = function() {
		var ww = document.body.clientWidth;
		if (ww < 768 && !width_is_mobile)
		{
			width_is_mobile = true; //verhindert mehrfaches Ausfuehren bei jeder Groessenaenderung
			//$('#dpma-carousel-controls').hide();
			if(!carousel_is_paused)
			{
				$('#dpma-carousel').carousel('pause');
				//carousel_is_paused = true;
			}
		} 
		else if (ww >= 768 && width_is_mobile)
		{
			width_is_mobile = false;
			//$('#dpma-carousel-controls').show();
			if(!carousel_is_paused)
			{
				$('#dpma-carousel').carousel('cycle');
				//carousel_is_paused = false;
			}
		}
	};
	$(window).resize(function(){
		stopCarouselOnMobile();
	});
	//Beim ersten Aufruf der Seite einmalig ausfuehren (zur Mobile-Erkennung)
	stopCarouselOnMobile();
});

// ############################################################################





/*

$(document).ready(function($) {
  var alterClass = function() {
    var ww = document.body.clientWidth;
    if (ww < 768) {
      $('#hauptnav').addClass('hauptnav-mobil');
      $('#hauptnav').removeClass('hauptnav-desktop');
    } else if (ww >= 768) {
      $('#hauptnav').removeClass('hauptnav-mobil');
      $('#hauptnav').addClass('hauptnav-desktop');
    };
  };
  $(window).resize(function(){
    alterClass();
  });
  //Fire it when the page first loads:
  alterClass();
});

*/






// ##### Dropdown-Link aufrufbar machen, wenn aufgeklappt #####################

$(document).ready(function() {
	$('#hauptnav li.dropdown a.dropdown-toggle').on('click', function() {
		var $a = $(this);
		var $li = $a.parent();
		if ($li.hasClass('open')) {
			if ($a.length && $a.attr('href')) {
				location.href = $a.attr('href');
			}
		}
	});
});

// ############################################################################




/*
 *  Bootstrap Dropdown Hover - v1.0.4
 *  Open dropdown menus on mouse hover, the proper way.
 *  http://www.virtuosoft.eu/code/bootstrap-dropdown-hover/
 *
 *  Made by István Ujj-Mészáros
 *  Under Apache License v2.0 License
 */
!function(a,b,c,d){function e(b,c){this.element=a(b),this.settings=a.extend({},j,c),this._defaults=j,this._name=i,this.init()}function f(b){a("body").one("touchstart.dropdownhover",function(){l=!0}),a("body").one("mouseenter.dropdownhover",function(){l||(m=!0)}),a(".dropdown-toggle, .dropdown-menu",b.element.parent()).on("mouseenter.dropdownhover",function(){m&&!a(this).is(":hover")&&(m=!1),m&&(clearTimeout(h),b.element.parent().hasClass("open")||(k=!1,b.element.dropdown("toggle")))}),a(".dropdown-toggle, .dropdown-menu",b.element.parent()).on("mouseleave.dropdownhover",function(){m&&(k||(h=setTimeout(function(){b.element.parent().hasClass("open")&&b.element.dropdown("toggle")},b.settings.hideTimeout)))}),b.element.on("click.dropdownhover",function(a){if(m)switch(b.settings.clickBehavior){case"default":return;case"disable":a.preventDefault(),a.stopImmediatePropagation();break;case"sticky":return void(k?k=!1:(k=!0,b.element.parent().hasClass("open")&&(a.stopImmediatePropagation(),a.preventDefault())))}})}function g(b){a(".dropdown-toggle, .dropdown-menu",b.element.parent()).off(".dropdownhover"),a(".dropdown-toggle, .dropdown-menu",b.element.parent()).off(".dropdown"),b.element.off(".dropdownhover"),a("body").off(".dropdownhover")}var h,i="bootstrapDropdownHover",j={clickBehavior:"sticky",hideTimeout:200},k=!1,l=!1,m=!1;e.prototype={init:function(){return this.setClickBehavior(this.settings.clickBehavior),this.setHideTimeout(this.settings.hideTimeout),f(this),this.element},setClickBehavior:function(a){return this.settings.clickBehavior=a,this.element},setHideTimeout:function(a){return this.settings.hideTimeout=a,this.element},destroy:function(){return clearTimeout(h),g(this),this.element.data("plugin_"+i,null),this.element}},a.fn[i]=function(b){var f=arguments;if(b===d||"object"==typeof b)return a.contains(c,a(this)[0])||a('[data-toggle="dropdown"]').each(function(c,d){a(d).bootstrapDropdownHover(b)}),this.each(function(){a(this).hasClass("dropdown-toggle")&&"dropdown"===a(this).data("toggle")?a.data(this,"plugin_"+i)||a.data(this,"plugin_"+i,new e(this,b)):a('[data-toggle="dropdown"]',this).each(function(c,d){a(d).bootstrapDropdownHover(b)})});if("string"==typeof b&&"_"!==b[0]&&"init"!==b){var g;return this.each(function(){var c=a.data(this,"plugin_"+i);c instanceof e&&"function"==typeof c[b]&&(g=c[b].apply(c,Array.prototype.slice.call(f,1)))}),g!==d?g:this}}}(jQuery,window,document);


// ##### Dropdown-Hover-Plugin aufrufen #######################################
$(document).ready(function() {
	$('#hauptnav .dropdown-toggle').bootstrapDropdownHover();
	
	// --- Deaktivieren in mobiler Ansicht ------------------
	var dropdownhoverplugin_active = true;
		
	var toggleDropdownHoverMobile = function(){
		var ww = document.body.clientWidth;
		if (ww < 768 && dropdownhoverplugin_active) {
			dropdownhoverplugin_active = false;
			$('#hauptnav .dropdown-toggle').bootstrapDropdownHover('destroy');
			//alert("Fenster kleiner als 768px (=Mobile). DropdownHoverPlugin wurde deaktiviert.");
		}
		else if (ww >= 768 && !dropdownhoverplugin_active) {
			dropdownhoverplugin_active = true;
			$('#hauptnav .dropdown-toggle').bootstrapDropdownHover();
			//alert("Fenster größer gleich 768px (=Desktop). DropdownHoverPlugin wurde aktiviert.");
		}
	};
	
	$(window).resize(function(){
		toggleDropdownHoverMobile();
	});
	
	//Einmalig aufrufen, um an initiale Bildschirmgroesse anzupassen
	toggleDropdownHoverMobile();
	// ------------------------------------------------------
});
// ############################################################################


// ##### Fokus beim verlassen des Hauptmenues wieder entfernen ################

$(document).ready(function() {
	$('#hauptnav .dropdown').on('mouseleave', function() {
		$('#hauptnav .dropdown > a').blur();
	});
});

// ############################################################################




// ##### Mobile Haupt- und Service-Navigation #################################

$(document).ready(function() {
	var $servicenav = $("ul#servicenav");
	var $servicenav_mobil = $servicenav.clone();
	var $dpma_hauptnavigation = $('#dpma-hauptnavigation');
	var $dpma_menu_button = $('#dpma-menu-button');
	var $metanav = $('#metanav');

	$servicenav_mobil.addClass("visible-xs-block");
	$servicenav.addClass("hidden-xs");

	$servicenav_mobil.attr('id', "servicenav-mobil").addClass('nav navbar-nav');
	$servicenav_mobil.appendTo($dpma_hauptnavigation);

	$dpma_menu_button.prependTo($metanav);
	$dpma_menu_button.wrap('<div class="row"></div>');
});

// ############################################################################







// ##### Unternavigation in mobiler Ansicht einklappen ########################

$(document).ready(function() {
	// JS nur ausfuehren, wenn #unternavbox und #bereichskennz auf Seite
	if( $('#unternavbox').length > 0 && $('#bereichskennz').length > 0 )
	{
		var lang = $('html').attr('lang') == "en" ? "en" : "de";
		
		var $unternavbox = $('#unternavbox');
		var $bereichskennz = $('#bereichskennz');
	
		var button_beschriftung = (lang == "en") ? "submenu" : "Unternavigation";
		
		var button_html = '<button id="unternav_collapse_button" class="btn btn-default btn-sm hidden-md hidden-lg" '+
							'data-toggle="collapse" aria-expanded="false" aria-controls="unternavbox" data-target="#unternavbox">'+
							'<span class="glyphicon glyphicon-align-left" aria-hidden="true"></span> ' + button_beschriftung + ' <span class="caret"></span></button>';
		
		// Ist initiale Browsergroesse XS oder SM? (< 992)
		var width_is_sm = (document.body.clientWidth < 992);
	
		// Button einfuegen
		$('#bereichskennz .container').append(button_html);
				
		// Unternavigation einklappen bei Mobil-Version (xs + sm)
		if(width_is_sm)
		{
			$unternavbox.addClass("collapse").attr("aria-expanded","true");
		}

		var changeUnternavboxCollapseOnResize = function() {
			var ww = document.body.clientWidth;
			if (ww < 992 && !width_is_sm)
			{
				width_is_sm = true; //verhindert mehrfaches Ausfuehren bei jeder Groessenaenderung
				//console.log("Wechsel:  Desktop -> Mobil  (< 992)");
				$unternavbox.addClass("collapse").attr("aria-expanded","true");
			} 
			else if (ww >= 992 && width_is_sm)
			{
				width_is_sm = false;
				//console.log("Wechsel:  Mobil -> Desktop  (>= 992)");
				$unternavbox.removeClass("collapse").removeClass("in").removeAttr("aria-expanded").removeAttr("style");
					// removeAttr("style") -> Inline-Styles entfernen (z. B. height: 0px)
			}
		};
		$(window).resize(function(){
			changeUnternavboxCollapseOnResize();
		});
	}
});

// ############################################################################

