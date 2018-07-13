/*-----------------------------------------------------------------------------------*/
/*	Flip Book JS 1.2.5
/*-----------------------------------------------------------------------------------*/

(function () {

	/* Basic Settings */
	
	// define pages (default are numbers 1-N)
	var pages = [],
		tocIndex = 3, /* table of content index */
		zoomStrength = 2, /* zoom strength */
		slideShowInterval = 2000, /* slide show delay in miliseconds */
		slideshowTimeout;
	
	/* do NOT EDIT bellow this line */
	
	// check hash
	function checkHash(hash) {
		var hash = getHash(), 
			k, 
			intRegex = /^\d+$/;
		
		if (( k = jQuery.inArray(hash, pages)) != -1) {
			k = k+1;
		} else if (intRegex.test(hash)) {
			k = hash;
		} else {
			k = 1;
		}
		
		return k;
	}
	
	// get url 
	function getURL() {
			return window.location.href.split('#').shift();
	}
	
	// get hash tag from url
	function getHash() {
			return window.location.hash.slice(1);
	}
	
	// set hash tag
	function setHashTag(flipbook) {
		var currentID = flipbook.turn('page'),
			pageURL;
			
		if(pages[currentID] != "" && pages[currentID] != undefined) {
			pageURL = getURL() + '#' + pages[currentID];
		} else {
			if(currentID % 2 != 0)
				pageURL = getURL() + '#' + currentID;
			else if(currentID == flipbook.data().totalPages)
				pageURL = getURL() + '#' + currentID;
			else
				pageURL = getURL() + '#' + (currentID + 1);	
		}
			
		window.location.href = pageURL;
	}
	
	// fired when hash changes inside URL
	function hashChange(flipbook, fbCont) {
		var page = checkHash(),
			position = flipbook.position();
			
		page = parseInt(page);
		
		if(page-1 == flipbook.turn('page')) // check if the current page is ok
			return;
			
		if(page == flipbook.data().totalPages) {
			disableShadows(flipbook);
			flipbook.turn('page', page);
		} else if(page % 2 != 0) {
			disableShadows(flipbook);
			flipbook.turn('page', page);
		} else {
			disableShadows(flipbook);
			flipbook.turn('page', (page+1));	
		}
	}
	
	// add inside book shadow
	function addInsideBookShadow(flipbook) {
		
		// inside book shadow
		flipbook.find('div.even div.fb-page').prepend('<div class="fb-inside-shadow-left"></div>');
		flipbook.find('div.odd div.fb-page').prepend('<div class="fb-inside-shadow-right"></div>');
		flipbook.find('div.last div.fb-page').prepend('<div class="fb-inside-shadow-left"></div>');
		flipbook.find('div.first div.fb-page').prepend('<div class="fb-inside-shadow-right"></div>');
	
		// edge page shadow
		flipbook.find('div.even div.fb-page').prepend('<div class="fb-page-edge-shadow-left"></div>');
		flipbook.find('div.odd div.fb-page').prepend('<div class="fb-page-edge-shadow-right"></div>');
		
		//bottom book page (under pages)
		flipbook.append('<div class="fb-shadow-bottom-left"></div>');
		flipbook.append('<div class="fb-shadow-bottom-right"></div>');
		flipbook.append('<div class="fb-shadow-top-left"></div>');
		flipbook.append('<div class="fb-shadow-top-right"></div>');
	}
	
	// hide shadows
	function hideShadows(caller, arrow, flipbook, fbCont, posLeft) {
		var view = flipbook.turn('view'),
			page = flipbook.turn('page'),
			active,
			nextY, nextYend,
			prevY, prevYend,
			posLeft,
			animate = false;
	
		if(animate)
			return;	
	
		if(isNaN(posLeft)){
			posLeft = parseInt(flipbook.position().left);
		} else {
			animate = true;
		}
		
		if(caller == 'start' || animate)
			active = flipbook.activePageCorner();
		
		if((caller == 'start' && active != 'tl' && active != 'bl' && active != 'tr' && active != 'br') ||
			(animate && active != 'tl' && active != 'bl' && active != 'tr' && active != 'br')) {
			active = arrow;
		}
	
		flipbook.children('div.fb-shadow-top-left').stop().clearQueue();
		flipbook.children('div.fb-shadow-bottom-left').stop().clearQueue();
		flipbook.children('div.fb-shadow-top-right').stop().clearQueue();
		flipbook.children('div.fb-shadow-bottom-right').stop().clearQueue();
		
		fbCont.find('div.preview').stop(true, true);
		fbCont.find('div.next').stop(true, true);
	
		if(fbCont.find('div.preview').hasClass('small')) {
			nextY = posLeft + flipbook.width() - 40;
			prevY = posLeft - 45;
			nextYend = posLeft + flipbook.width() - 50;
			prevYend = posLeft;
		} else {
			nextY = posLeft + flipbook.width() + 24;
			prevY = posLeft - 110;
			nextYend = posLeft + flipbook.width() - 50;
			prevYend = posLeft;
		}

		if(page == 2 && animate && (active == 'tl' || active == 'bl' || active == 'left')){
			flipbook.children('div.fb-shadow-top-left').animate( { opacity: 0 }, 200);
			flipbook.children('div.fb-shadow-bottom-left').animate( { opacity: 0 }, 200);
		} else if (page == flipbook.data().totalPages - 2 && animate && (active == 'tr' || active == 'br' || active == 'right')) {
			flipbook.children('div.fb-shadow-top-right').animate( { opacity: 0 }, 200);
			flipbook.children('div.fb-shadow-bottom-right').animate( { opacity: 0 }, 200);
		}
		
		if(animate)
			return;
		
		if (page == 2 && (active == 'tl' || active == 'bl' || active == 'left')) {
			flipbook.children('div.fb-shadow-top-left').animate( { opacity: 0 }, 200);
			flipbook.children('div.fb-shadow-bottom-left').animate( { opacity: 0 }, 200);
			
			fbCont.find('div.preview').animate( {
				opacity: 0,
				left: prevYend
			}, 500, 'easeOutExpo', function(){
				animate = false;
			});
		} else if(page != 1) {
			flipbook.children('div.fb-shadow-top-left').animate( { opacity: 1 }, 500);
			flipbook.children('div.fb-shadow-bottom-left').animate( { opacity: 1 }, 500);
			fbCont.find('div.preview').animate( {
				opacity: 1,
				left: prevY
			}, 500, 'easeOutExpo', function(){
				animate = false;
			});
		} 
		
		if (page == flipbook.data().totalPages - 2 && (active == 'tr' || active == 'br' || active == 'right')) {
			flipbook.children('div.fb-shadow-top-right').animate( { opacity: 0 }, 200);
			flipbook.children('div.fb-shadow-bottom-right').animate( { opacity: 0 }, 200);
			fbCont.find('div.next').animate( {
				opacity: 0,
				left: nextYend
			}, 500, 'easeOutExpo', function(){
				animate = false;
			});
		} else if(page != flipbook.data().totalPages) {
			flipbook.children('div.fb-shadow-top-right').animate( { opacity: 1 }, 500);
			flipbook.children('div.fb-shadow-bottom-right').animate( { opacity: 1 }, 500);
			fbCont.find('div.next').animate( {
				opacity: 1,
				left: nextY
			}, 500, 'easeOutExpo', function(){
				animate = false;
			});
	 	}
	}
	
	function repositionArrows(flipbook, fbCont) {
		var posLeft, nextY, prevY, nextYend, prevYend, page;

		page = flipbook.turn('page');
		posLeft = parseInt(flipbook.position().left);

		if(fbCont.find('div.preview').hasClass('small')) {
			nextY = posLeft + flipbook.width() - 40;
			prevY = posLeft - 45;
			nextYend = posLeft + flipbook.width() - 50;
			prevYend = posLeft;
		} else {
			nextY = posLeft + flipbook.width() + 24;
			prevY = posLeft - 110;
			nextYend = posLeft + flipbook.width() - 50;
			prevYend = posLeft;
		}
		
		if (page == 1) {
			fbCont.find('div.preview').animate( {
				opacity: 0,
				left: prevYend
			}, 200, 'easeOutExpo');
		} else {
			fbCont.find('div.preview').animate( {
				opacity: 1,
				left: prevY
			}, 200, 'easeOutExpo');
		} 
		
		if (page == flipbook.data().totalPages) {
			fbCont.find('div.next').animate( {
				opacity: 0,
				left: nextYend
			}, 200, 'easeOutExpo');
		} else {
			fbCont.find('div.next').animate( {
				opacity: 1,
				left: nextY
			}, 200, 'easeOutExpo');
	 	}
	}

	function rotated() {	
		return Math.abs(window.orientation)==90;
	}
	
	function resizeFB(fbWidth, fbHeight, flipbook, fbCont, zoomed) {
		var singleWidth,
			singleHeight;
		
		flipbook.turn('size', fbWidth, fbHeight);
		
		singleWidth = flipbook.find('div.turn-page-wrapper.first').width();
		singleHeight = flipbook.find('div.turn-page-wrapper.first').height();
		
		if(zoomed) {
			var largeImage = false;

			zoomCont = fbCont.find('div.zoomed');

			fbZoomedBorder = parseInt(zoomCont.css('border-left-width'));
			
			var fbOffset = fbCont.offset();
			fbTopMargin = fbOffset.top;
			
			if(zoomCont.find('img.bg-img').hasClass('zoom-large'))
				largeImage = true;
				
			if(!largeImage) {
				zoomCont.find('img.bg-img').css({
					'margin-top': '0px',
					'opacity': 1
				});
				zoomCont.find('img.bg-img.zoom-large').css('opacity', 0);
			} else {
				zoomCont.find('img.bg-img').css('display', 'none');
				
				zoomCont.find('img.bg-img.zoom-large').css({
					'margin-top': '0px',
					'opacity': 1,
					'display': 'block'
				});	
			} 
			
			zoomCont.children().css('margin-top', 0);

			// add classes from the page parent
			if(jQuery(this).find('div.fb-page').hasClass('double')){
				zoomCont.addClass('double');
			}
			
			if(jQuery(this).hasClass('odd')){
				zoomCont.addClass('odd');
			}
					
			// zoom container size
			zoomCont.width(flipbook.width() * 0.5 * zoomStrength);
			zoomCont.height(flipbook.height() - (fbZoomedBorder * 2)); 
			
			zoomCont.find(' > div, img.bg-img, div.video').each(function() {
				var $this = jQuery(this);
				if($this.hasClass('video')){
					//$this.css('height', $this.height() * zoomStrength + "px");
				} else {
					if(zoomCont.hasClass('double')){
						$this.width(100 * zoomStrength + '%');
						$this.height(flipbook.find('div.first').height() * zoomStrength);
					} else {
						$this.width(flipbook.find('div.first').width() * zoomStrength);
						$this.height(flipbook.find('div.first').height() * zoomStrength);
					}	
				}
			});
			
			zoomCont.find('.enlarge').each(function() {
				var $this = jQuery(this),
					enlargeHeight = $this.height() *  zoomStrength;
				
				$this.css({
					'font-size': Math.round(parseInt($this.css('font-size')) * zoomStrength) + "px" ,
					'line-height': Math.round(parseInt($this.css('line-height')) * zoomStrength) + "px"
				});
			});
			
			// set img.bg-img
			zoomCont.find('img.bg-img').width(flipbook.find('div.first').width() * zoomStrength);
			zoomCont.find('img.bg-img').height(flipbook.find('div.first').height() * zoomStrength);

			fbCont.find('div.zoomed.double img.bg-img').width(flipbook.find('div.first').width() * 2 * zoomStrength);
			fbCont.find('div.zoomed.double.odd img.bg-img').css('margin-left', '0px');
			fbCont.find('div.zoomed.double img.bg-img').css({
				'left' : '0px',
				'right' : '0px' 
			});
			
			zoomCont.css('left', (fbCont.width() - zoomCont.outerWidth()) / 2);
			fbCont.find('div.zoomed-shadow').css({
				'left': (parseInt(zoomCont.css('left')) + fbZoomedBorder),
				'top': (parseInt(zoomCont.css('top')) + fbZoomedBorder),
				'width': zoomCont.css('width'),
				'height': zoomCont.css('height')
			});
			fbCont.find('div.zoomed-shadow-top').css({
				'left': (parseInt(zoomCont.css('left')) + fbZoomedBorder),
				'width': zoomCont.css('width')
			});
			fbCont.find('div.zoomed-shadow-bottom').css({
				'left': (parseInt(zoomCont.css('left')) + fbZoomedBorder),
				'top': zoomCont.height() - 40 + fbZoomedBorder * 2,
				'width': zoomCont.css('width')
			});
		}

		flipbook.find('div.page-content').each(function () {
			var $this = jQuery(this);
			$this.width(singleWidth - parseInt($this.css('margin-top')));
			$this.height(singleHeight - (parseInt($this.css('margin-top')) * 2));
			$this.find('img.bg-img').height($this.height());
			if($this.find('object, iframe').length)
				$this.find('.preview-content').height('100%');
			if($this.parent().hasClass('double')) {
				$this.find('img.bg-img').width($this.width() * 2);
	
				if($this.parent().parent().parent().hasClass('odd')) {
					rightMargin = parseInt($this.css('margin-right')); 
					$this.find('img.bg-img').css('margin-left', - $this.width() + "px");
					$this.find('div.container img.bg-img').css('margin-left', "0px");
				}
			} else {
				$this.find('img.bg-img').width($this.width());
			}
			$this.parent().find('div.fb-inside-shadow-left').height($this.height());
			$this.parent().find('div.fb-inside-shadow-right').height($this.height());
			$this.parent().find('div.fb-page-edge-shadow-left').height($this.height());
			$this.parent().find('div.fb-page-edge-shadow-right').height($this.height());
		});
		
		flipbook.find('div.fb-shadow-bottom-left').width(fbWidth/2);
		flipbook.find('div.fb-shadow-bottom-right').width(fbWidth/2);
		flipbook.find('div.fb-shadow-top-left').width(fbWidth/2);
		flipbook.find('div.fb-shadow-top-right').width(fbWidth/2);
		fbCont.find('div.preview, div.next').css('top', (flipbook.find('div.turn-page-wrapper.first').height() - 86) / 2);
	}
	
	
	function centerBook(page, flipbook, fbCont, activeArrow) {
		var rendered = flipbook.data().done,
			width = flipbook.width(),
			pageWidth = width/2,
			options = {	duration: (!rendered) ? 0 : 600, 
						easing: 'easeOutExpo', 
						complete: function() { 
							flipbook.turn('resize');
						}
					 };
		
		flipbook.stop(true);
		
		if ((page == 1 || page == flipbook.data().totalPages)) {
			var left;
	
			if(page == 1) 
				left = Math.floor((fbCont.width() - pageWidth) * 0.5) - pageWidth;
			else
				left = Math.floor((fbCont.width() - pageWidth) * 0.5);
		
			if(parseInt(flipbook.css('left')) != left){
				flipbook.animate({left: left }, options);
				
				hideShadows('center', activeArrow, flipbook, fbCont , Math.floor((fbCont.width() - pageWidth) * 0.5) - pageWidth );
			}
			
		} else {
			flipbook.animate({left: Math.floor((fbCont.width() - width) * 0.5) }, options);
			
			hideShadows('center', activeArrow, flipbook, fbCont , Math.floor((fbCont.width() - width) * 0.5));
		}	
	}
	
	
	function fbFirstRun(flipbook, fbCont) {
		// resize the book
		jQuery(window).trigger('resize');
		fbCont.find('div.next').stop().clearQueue();
		fbCont.find('div.preview').stop().clearQueue();
		fbCont.find('div.preview').css('left', 0);
		fbCont.find('div.next').css('left', jQuery(window).width() * 0.5);
		// adjust shadows
		hideShadows('turned', 'false', flipbook, fbCont, 'first run');		
		
		// show bottom ui
		if(jQuery('div.fb-nav').hasClass('small')){
			jQuery('div.fb-nav').animate( {
				opacity: 1,
				top: - 40 - parseInt(flipbook.css('margin-bottom').replace("px", "")) - parseInt(jQuery('div.fb-nav').css('margin-top').replace("px", ""))
			}, 1000, 'easeOutExpo');
		} else {
			jQuery('div.fb-nav').animate( {
				opacity: 1,
				top: '0'
			}, 1000, 'easeOutExpo');
		}

		// remove preloader
		flipbook.parent().css('background-image', 'none');
	}
	
	function fbOut(flipbook, activeCorner) {
		flipbook.find('div.turn-page-wrapper').each(function (){
			$(this).children('div:first-child').width(flipbook.width() * 0.5 + 'px');
			$(this).children('div:first-child').height(flipbook.height() + 'px');
		});
	
	}
	
	function disableShadows(flipbook) {
		flipbook.find('div.fb-shadow-bottom-left').css('opacity', 0);
		flipbook.find('div.fb-shadow-top-left').css('opacity', 0);
		flipbook.find('div.fb-shadow-bottom-right').css('opacity', 0);
		flipbook.find('div.fb-shadow-top-right').css('opacity', 0);
	}
	
	jQuery(document).ready(function($) {
		var flipbook = $('.flipbook'),
			fbCont = $('div.flipbook-container'),
			fbNav = fbCont.find('div.fb-nav'),
			slideshow = false,
			zoomed = false,
			activeArrow = 'false',
			pageID = 0,
			lastID,
			firstWidth,
			firstHeight,
			activeCorner = false,
			fbOver = false,
			pageTurning = false,
			ie = false,
			touch = 'ontouchstart' in document.documentElement,
			hash,
			$swap;
		
		if(navigator.appName == "Microsoft Internet Explorer")
			ie = true;
		
		flipbook.bind('turning', function(e, page) {
			pageTurning = true;
		});
	
		
		flipbook.bind('turned', function(e, page) {
			var $this = $(this);
			
			var rendered = $this.data().done;
			
			centerBook(page, flipbook, fbCont, activeArrow);	
			
			fbCont.find('div.preview, div.next').css('top', (flipbook.find('div.turn-page-wrapper.first').height() - 86) / 2);
			
			
			if(slideshow) {
				slideshowTimeout = setTimeout(function() {
					$this.turn('next');

					if(flipbook.turn('page') + 2 >= flipbook.data().totalPages) { // turn off slide show on last slide
					
						slideshow = false;
						fbNav.find('ul li.slideshow span.button-icon-over').animate( { opacity : 0 }, 300);
						fbNav.find('ul li.slideshow span.button-icon').animate( { opacity : 1 }, 300);
						fbNav.find('ul li.slideshow').trigger('mouseout');

						setTimeout(function() {
							fbCont.find('div.next, div.preview').fadeIn(500);
						}, 600);

						hideShadows('start', 'right', flipbook, fbCont, 'end');
					}
				}, slideshowDelay);
			}
			
			hideShadows('turned', 'false', flipbook, fbCont, 'end');	
			setTimeout(function() {
				repositionArrows(flipbook, fbCont);
			}, 350);
			pageTurning = false;
		});	
				
		/* Duplicate Double Pages */
		flipbook.find('div.fb-page').each(function() {
			var $this = $(this);
			
			if($this.hasClass('double')){
				clone = $this.clone(true);
				clone.insertAfter($this);
			} 
		});
		
		/* Initialize Flip Book */
		hash = checkHash();
		if(hash != 1 && pages.length < 2) {
			flipbook.turn({
				page: hash+1, // define start page,
				acceleration: true, // enable hardware acceleration,
				shadows: !$.isTouch, // enable/disable shadows,
				duration: 500, // page flip duration.
			});
		} else {
			flipbook.turn({
				page: hash, // define start page,
				acceleration: true, // enable hardware acceleration,
				shadows: !$.isTouch, // enable/disable shadows,
				duration: 500, // page flip duration.
			});
		}

		/* Add Class for Even and Odd Pages */
		flipbook.find('div.turn-page-wrapper').each(function() {
			var $this = $(this),
				pageID = $(this).attr('page'),
				lastID = flipbook.data().totalPages,
				clone;
			
			if(pageID == 1) {
				$this.addClass('first');
				$this.find('div.page-content').addClass('first');
			} else if(pageID == lastID){
				$this.addClass('last');
				$this.find('div.page-content').addClass('last');
			} else if(pageID % 2 == 0) {
				$this.addClass('even');
				$this.find('div.page-content').addClass('even');
			} else {
				$this.addClass('odd');
				$this.find('div.page-content').addClass('odd');
			}
		
			
			if(pageID % 2 != 0 && pageID != 1 && pageID != lastID) {
				rightMargin = parseInt($this.find('div.double div.page-content').css('margin-right'));
				$this.find('div.double div.page-content img.bg-img').css('margin-left', - $this.width() + rightMargin +"px");
				$this.find('div.container img.bg-img').css('margin-left', "0px");
			}
		});
		
		/* If double page set properly the odd page container */
		flipbook.find(' > div:last-child > div').each(function() {
			var $this = $(this);
			
			pageID ++;
			lastID = flipbook.data().totalPages;
			
			$this.addClass('page-transition')
			$this.attr('page', pageID);
			
			if(pageID == 1)
				$this.addClass('first');
			else if(pageID == lastID)
				$this.addClass('last');
			else if(pageID % 2 == 0)
				$this.addClass('even');
			else
				$this.addClass('odd');
		});
	
		var tpwWidth = flipbook.find('div.turn-page-wrapper > div').width(),
			tpwHeight = flipbook.find('div.turn-page-wrapper > div').height();
		
		/*-----------------------------------------------------------------------------------*/
		/* Zoom	
		/*-----------------------------------------------------------------------------------*/
		

		/*-----------------------------------------------------------------------------------*/
		/*	Flip Book Navigation
		/*-----------------------------------------------------------------------------------*/
		
		var slideshowDelay = slideShowInterval,
			navWidth = 0,
			i = 0;
			
		fbNav.find('ul li').each(function() {
			var $this = $(this);
			
			if($this.hasClass('left')) {
				navWidth += 97;
			} else if ($this.hasClass('center')) {
				navWidth += 89;
			} else if ($this.hasClass('right')) {
				navWidth += 95;
			} else if ($this.hasClass('round')) {
				if(i == 0) {
					navWidth += 96;
					$this.css('margin-left', '0px');
				 } else { 
					navWidth += 96;
				}
			}
			i++;
		});
		
		fbNav.width(navWidth);
	
		fbNav.find('ul li').each(function(){
			var $this = $(this);
			if($this.hasClass('slideshow')) {
				$this.append('<span class="button"></span><span class="button-over"></span><span class="button-icon"></span><span class="button-icon-over"></span>');
			} else {
				$this.append('<span class="button"></span><span class="button-over"></span><span class="button-icon"></span>');
			}
		});
		
		fbCont.append('<div class="next round"><span class="button"></span><span class="button-over"></span><span class="button-icon"></span></div><div class="preview round"><span class="button"></span><span class="button-over"></span><span class="button-icon"></span></div>');
		
		if(fbNav.hasClass('mobile')){
			fbCont.find('div.next, div.preview').addClass('mobile');
		}
		
		$('.fb-nav ul li, .flipbook-container div.next, .flipbook-container div.preview').hover(function(){
			var	$this = $(this);
					
			$this.children('span.button').clearQueue();
			$this.children('span.button-over').clearQueue();
			$this.children('span.button').animate( { opacity : 0 }, 300);
			$this.children('span.button-over').animate( { opacity : 1 }, 300);
		}, function() {
			var	$this = $(this);
			if($this.hasClass('slideshow') && slideshow || $this.hasClass('zoom') && zoomed) {
				//do nothing
			} else {
				$this.children('span.button').animate( { opacity : 1 }, 300);
				$this.children('span.button-over').animate( { opacity : 0 }, 300);
			}
		});
		
		// Table Of Content
		fbNav.find('ul li.toc').on('click', function(e){
			if(flipbook.turn('page') != tocIndex)
				flipbook.trigger('mouseover');
				flipbook.turn('page', tocIndex);
				disableShadows(flipbook);
	
		});
		
		// Zoom
		fbNav.find('ul li.zoom').on('click', function(e){
			flipbook.find('div.turn-page-wrapper').each(function() {
				var $this = $(this);
				if($this.attr('page') == flipbook.turn('page') && !zoomed)
					$this.trigger('dblclick');
				else if($this.attr('page') == flipbook.turn('page') && zoomed)
					fbCont.find('div.zoomed').trigger('dblclick');
			});
		});
	
		// Slideshow	
		fbNav.find('ul li.slideshow').on('click', function(e){
			slideshow = !slideshow;
			var $this = $(this);
			if(slideshow) {
				clearTimeout(slideshowTimeout);
				flipbook.turn('next');
				fbCont.find('div.next, div.preview').stop(true).fadeOut(500);
				
				$this.children('span.button-icon-over').animate( { opacity : 1 }, 300);
				$this.children('span.button-icon').animate( { opacity : 0 }, 300);
			} else {
				clearTimeout(slideshowTimeout);
				fbCont.find('div.next, div.preview').stop(true).fadeIn(500);

				$this.children('span.button-icon-over').animate( { opacity : 0 }, 300);
				$this.children('span.button-icon').animate( { opacity : 1 }, 300);
			}
			hideShadows('start', 'right', flipbook, fbCont, 'end');
		});
		
		// Show All Pages
		fbNav.find('ul li.show-all').on('click', function(e){
			var fbHeight = flipbook.height(),
				fbWidth = flipbook.width(),
				paddingAround = 12,
				paddingVertical,
				paddingHorizontal,
				thumbHeight,
				thumbWidth,
				row = 6,
				ind = 1,
				col = 10,
				clone,
				percentage,
				borderSize;
			
			flipbook.turn('stop');
			
			fbCont.append('<div class="show-all"><div class="content"></div></div>');
			
			fbCont.append('<div class="showall-shadow-top"></div><div class="showall-shadow-bottom"></div>');
			fbCont.find('div.preview, div.next').animate( { opacity: 0 }, 300);
				
			var showAll = fbCont.find('div.show-all');
			
			// add next
			fbCont.append('<span class="big-next show-all-next"><span class="arrow"></span><span class="left"></span><span class="center"></span><span class="right"></span><span class="left hover"></span><span class="center hover"></span><span class="right hover"></span></span>');
			
			// add previous
			fbCont.append('<span class="big-next show-all-previous"><span class="arrow"></span><span class="left"></span><span class="center"></span><span class="right"></span><span class="left hover"></span><span class="center hover"></span><span class="right hover"></span></span>');
			
			ind = 2;
			flipbook.find('div.page-content').each(function(){
				var $this = $(this);
				if(!$this.parent().parent().hasClass('fpage')) {
					clone = $this.clone().addClass('show-all-thumb');
					clone.find('img.bg-img.zoom-large').remove();
					clone.attr('style', '');
					// add odd pages class
					
					if($this.parent().parent().parent().hasClass('last'))
						clone.addClass('even');
					else if($this.parent().parent().parent().hasClass('first'))
						clone.addClass('odd');	
	
					// if page double add class double
					if($this.parent().hasClass('double')) clone.addClass('double');
					// append clone to show-all div
					if($this.parent().parent().parent().hasClass('last'))
						showAll.find('div.content').prepend(clone);
					else
						showAll.find('div.content').append(clone);
					
					ind++;
				} 
			});
			
			paddingAround = parseInt(showAll.find('div.content').css('top'));
			paddingVertical = parseInt(showAll.find('div.show-all-thumb').css('margin-bottom'));
			paddingHorizontal = parseInt(showAll.find('div.show-all-thumb.odd').css('margin-right'));
			thumbHeight = parseInt(showAll.find('div.show-all-thumb').css('height'));
			thumbWidth = parseInt(showAll.find('div.show-all-thumb').css('width'));
			borderSize = parseInt(showAll.find('div.show-all-thumb.odd').css('border-right-width'));
			
			percentage = thumbHeight/fbHeight;
			
			// check how many rows
			while(fbCont.height() - 100 <= ((paddingAround * 2) + (row * (thumbHeight + borderSize*2 + paddingVertical)) - paddingVertical)){
				row--;
			}
			
			// check how many columns
			while(fbCont.width() - 100 <= ((paddingAround * 2) + (col * (thumbWidth+borderSize)) + (col * paddingHorizontal * 0.5)) - paddingHorizontal){
				col--;
			}
			
			if(col % 2 != 0)
				col--;
				
			if(col > 6)
				col = 6;
			
			showAll.css({
				'width':  ((paddingAround * 2) + (col * (thumbWidth+borderSize)) + (col * paddingHorizontal * 0.5)) - paddingHorizontal + 'px',
				'height': ((paddingAround * 2) + (row * (thumbHeight + borderSize*2 + paddingVertical)) - paddingVertical) +'px'
			});
			
			ind = 1;
			
			showAll.find('div.show-all-thumb').each(function(){
				var $this = $(this);
				// if last in the row add class last
				if(ind % col == 0) $this.addClass('last-thumb');
						
				ind++;
				
				
				$this.find('.enlarge').each(function(){
					$(this).css({
						'font-size': Math.round(parseInt($(this).css('font-size')) * percentage) + 'px',
						'line-height': Math.round(parseInt($(this).css('line-height')) * percentage) + 'px'
						});
					
				});
				
				$this.prepend('<span class="shadow"></span>');
				$this.find('div.video').remove();
			});
			
			
			flipbook.animate( { opacity: 0 }, 500, function() {
				flipbook.css('visibility', 'none');
			});
			
			fbCont.find('div.preview').animate( { opacity: 0 }, 300);
			fbCont.find('div.next').animate( { opacity: 0 }, 300);
			
			flipbook.css('pointer-events', 'none');
			fbNav.animate( { opacity: 0 }, 500);
			showAll.animate( { opacity: 1 }, 500);
			
			showAll.css({
				'left': (fbCont.width() - showAll.width()) / 2 + 'px'
			});
			
			fbCont.find('span.show-all-previous').css({
					'width': showAll.width(),
					'top': parseInt(showAll.css('top')) - 43 + 'px',
					'left': parseInt(showAll.css('left')) + 1 + 'px' 	
			});
			
			fbCont.find('span.show-all-next').css({
					'width': showAll.width(),
					'top': parseInt(showAll.css('top')) + parseInt(showAll.css('height')) - 33 + 'px',
					'left': parseInt(showAll.css('left')) + 1 + 'px' 	
			});
			
			fbCont.find('span.big-next').each(function() {
				$(this).find('span.center').width($(this).width() - 26);
			});
			
			if(showAll.height() < showAll.find('div.content').height()-paddingVertical){
				fbCont.find('span.show-all-previous').css('visibility', 'visible');
				fbCont.find('span.show-all-next').css('visibility', 'visible');
				fbCont.find('span.show-all-previous').animate( { opacity: 1 }, 500);
				fbCont.find('span.show-all-next').animate( { opacity: 1 }, 500);
			}
			
			/* Show All Events */
			showAll.find('div.show-all-thumb').hover( function(){ 
				var $this = $(this);
				$this.find('span.shadow').clearQueue();
				$this.find('span.shadow').animate( { opacity: 1 }, 300);
				
				if($this.hasClass('even')) {
					$this.next().find('span.shadow').clearQueue();
					$this.next().find('span.shadow').animate( { opacity: 1 }, 300);
				} else {
					$this.prev().find('span.shadow').clearQueue();
					$this.prev().find('span.shadow').animate( { opacity: 1 }, 300);
				}
				
			}, function(){
				var $this = $(this);
				
				$this.find('span.shadow').animate( { opacity: 0 }, 300);
				
				if($this.hasClass('even')) {
					$this.next().find('span.shadow').clearQueue();
					$this.next().find('span.shadow').animate( { opacity: 0 }, 300);
				} else {
					$this.prev().find('span.shadow').clearQueue();
					$this.prev().find('span.shadow').animate( { opacity: 0 }, 300);
				}
			});
			
			/* Show All Events */
			var position = showAll.position();
			fbCont.find('div.showall-shadow-top, div.showall-shadow-bottom').width(showAll.width()).delay(500).animate( { opacity: 1 }, 500);
	 		fbCont.find('div.showall-shadow-top').css({
	 			top: position.top - 12	,
	 			left: position.left + borderSize
	 		});
	 		
	 		fbCont.find('div.showall-shadow-bottom').css({
	 			top: position.top + showAll.height() - 39,
	 			left: position.left + borderSize
	 		});
	
	
			/* Thumbnail Click */
			showAll.find('div.show-all-thumb').on('click', function(){
				var $this = $(this),
					id = Math.ceil($this.index());
	
				flipbook.trigger('mouseover');
				disableShadows(flipbook);
				flipbook.turn('page', id);
				
				/* Close */
				showAll.animate( { opacity: 0 }, 300, function(){
					$(this).remove();
				});
				
				fbCont.find('div.showall-shadow-bottom').animate( { opacity: 0 }, 100, function(){
					$(this).remove();
				});
				
				fbCont.find('div.showall-shadow-top').animate( { opacity: 0 }, 100, function(){
					$(this).remove();
				});
				
				fbCont.find('span.show-all-previous').animate( { opacity: 0 }, 300, function(){
					$(this).remove();
				});
		
				fbCont.find('span.show-all-next').animate( { opacity: 0 }, 300, function(){
					$(this).remove();
				});
				
				flipbook.css('visibility', 'visible');
				flipbook.clearQueue();
				flipbook.animate( { opacity: 1 }, 300 );
				fbNav.animate( { opacity: 1 }, 500);
				
				if(id < 2){
					flipbook.find('div.fb-shadow-bottom-left').css('opacity', 0);
					flipbook.find('div.fb-shadow-top-left').css('opacity', 0);
				}
				
				flipbook.css('pointer-events', 'all');
			});
			
			var saContent = showAll.find('div.content'),
				scrollAmount = row * (thumbHeight + paddingVertical + borderSize * 2),
				animation = false;
			
			/* Scroll ShowAll */
			fbCont.find('span.show-all-previous').on('click', function() {
				if(parseInt(saContent.css('top')) != paddingAround && !animation) {
					animation = true;
					saContent.clearQueue();
					saContent.animate( { top: (parseInt(saContent.css('top')) + scrollAmount) }, 1000, 'easeOutExpo', function(){
						animation = false;
					});
				}
			});
			
			fbCont.find('span.show-all-next').on('click', function() {
				if(parseInt(saContent.css('top')) - scrollAmount > 12 - saContent.height() && !animation){
					animation = true;
					saContent.clearQueue();
					saContent.animate( { top: (parseInt(saContent.css('top')) - scrollAmount) }, 1000, 'easeOutExpo', function(){
						animation = false;
					});
				}
			});
			
			fbCont.find('span.show-all-next, span.show-all-previous').hover(function() {
				var but = $(this);
				but.clearQueue();
				but.find('span.left.hover, span.right.hover, span.center.hover').animate( { opacity: 1 }, 300);
			}, function() {
				var but = $(this);
				but.clearQueue();
				but.find('span.left.hover, span.right.hover, span.center.hover').animate( { opacity: 0 }, 300);
			});
			
		});
		
		/* Next & Previous */
		fbCont.find('div.next').on('click', function(e){
			if(pageTurning)
				return;
			activeArrow = 'right';	
			flipbook.trigger('mouseover');
			flipbook.turn('next');
		});
		
		fbCont.find('div.preview').on('click', function(e){
			if(pageTurning)
				return;
				
			activeArrow = 'left';
			flipbook.trigger('mouseover');
			flipbook.turn('previous');
		});	
	
		/*-----------------------------------------------------------------------------------*/
		/* Shadows	
		/*-----------------------------------------------------------------------------------*/
			
		addInsideBookShadow(flipbook);
		
		/*-----------------------------------------------------------------------------------*/
		/* Events	
		/*-----------------------------------------------------------------------------------*/
		
		hashChange(flipbook, fbCont);
		
		firstWidth = flipbook.width(),
		firstHeight = flipbook.height();
		var posArray = new Array(),
			i = 0;
		$('.flipbook div.page-content .enlarge').each(function() {
			posArray.push(new Array());
			posArray[i].push(parseInt($(this).css('font-size')));
			posArray[i].push(parseInt($(this).css('line-height')));
			i++;
		});
		
		/* Window Resize */
		$(window).on('resize', function() {
			var currentID = flipbook.turn('page');
	
			var $this = $(this),
				width = $this.width(), // window width
				height; // window height
				
			var fbNext = fbCont.find('div.next'),
				fbPrev = fbCont.find('div.preview'),
				ratio, fbWidth, fbHeight, fbPercentage,
				areaHeight = parseInt(flipbook.css('margin-top')) + parseInt(flipbook.css('margin-bottom')) + flipbook.height() + parseInt(fbNav.css('margin-top')) + parseInt(fbNav.css('margin-bottom')) + parseInt(fbNav.css('top')) + fbNav.height() + 40,
				areaWidth = flipbook.width() + 330, 
				areaMinHeight = parseInt(flipbook.css('margin-top')) + flipbook.height() + fbNav.height() - 55,
				areaMinWidth = flipbook.width() + 140,
				position = flipbook.position();

			if(fbCont.height() / firstHeight < fbCont.width() / firstWidth)
				ratio = fbCont.height() / firstHeight;
			else 
				ratio = fbCont.width() / firstWidth;

			fbWidth = fbCont.width();
			fbHeight = fbCont.height();
			
			if(fbWidth < firstWidth) {
				var ratio = fbWidth / firstWidth;

				fbWidth = firstWidth * ratio;
				fbHeight = firstHeight * ratio;
			}

			if(fbWidth > firstWidth || fbHeight > firstHeight) {
				fbWidth = firstWidth;
				fbHeight = firstHeight;
			}

			if(fbWidth < fbNav.width() + 20) {
				fbWidth = fbNav.width() + 20;
				fbHeight = firstHeight * (fbWidth / firstWidth);
			}

			if(fbWidth != flipbook.width() || fbHeight != flipbook.height())
				resizeFB(fbWidth, fbHeight, flipbook, fbCont, zoomed);
			
			if(height > areaHeight && width > areaWidth) {
				fbWidth = firstWidth;
				fbHeight = firstHeight;
				resizeFB(firstWidth, firstHeight, flipbook, fbCont, zoomed);
			}
			
			fbPercentage = flipbook.height() / firstHeight;
			fbPercentage = parseInt(fbPercentage * 100);
			
			var i = 0;
			
			
			$('.flipbook div.page-content .enlarge').each(function() {
				if(fbPercentage == 100) {
					$(this).css('font-size', posArray[i][0] + "px");
					$(this).css('line-height', posArray[i][1] + "px");
				} else {
					$(this).css('font-size', posArray[i][0] * (fbPercentage/100) + "px");
					$(this).css('line-height', posArray[i][1] * (fbPercentage/100) + "px");	
				}
				i++;
			});

			width = $this.width();

			areaWidth = fbWidth + 330; 
			
			areaMinHeight = parseInt(flipbook.css('margin-top').replace("px", "")) + fbHeight + fbNav.height() - 55;
			areaMinWidth = fbWidth + 140;
			
			if ((currentID == 1 || currentID == flipbook.data().totalPages)) {
				var left;
		
				if(currentID == 1) 
					left = Math.floor((fbCont.width() - flipbook.width()/2) * 0.5) - flipbook.width()/2;
				else
					left = Math.floor((fbCont.width() - flipbook.width()/2) * 0.5);
		
				if(parseInt(flipbook.css('left')) != left)
					flipbook.css({'left': left });
				
			} else {
				flipbook.css( {'left': Math.floor((fbCont.width() - flipbook.width()) * 0.5) });

				if(zoomed) {
					fbCont.children('div.zoomed').css( {'left': Math.floor((fbCont.width() - flipbook.width()) * 0.5) - 10 });
					fbCont.children('div.zoomed-shadow, div.zoomed-shadow-bottom, div.zoomed-shadow-top').css( {'left': Math.floor((fbCont.width() - flipbook.width()) * 0.5) });
				}
			}	

			/* Preview / Next Arrows */
			if(width < areaWidth ) {
				fbNext.addClass('small').css({ left: position.left + flipbook.width() - 40 });
				fbPrev.addClass('small').css({ left: position.left - 45 });
				fbPrev.find('span.button-icon').css({ left: 6 });
				fbNext.find('span.button-icon').css({ left: 41 });
			} else if (width > areaWidth) {
				fbNext.removeClass('small').css({ left: position.left + flipbook.width() + 24 });
				fbPrev.removeClass('small').css({ left: position.left - 110 });
				fbPrev.find('span.button-icon').css({ left: 22 });
				fbNext.find('span.button-icon').css({ left: 26 });
			}

			if(fbPrev.css('opacity') == "0")
				fbPrev.css('left', '100px'); 
			
			if(fbNext.css('opacity') == "0")
				fbNext.css('right', '100px'); 

			if(zoomed) {
				var l = parseInt(fbCont.find('div.zoomed').css('left'));
				
				fbCont.children('.big-side.show-previous')
					.css({
						'left': l - 40
					})
					.height(fbHeight)
					.children('.center, .center.hover')
						.height(fbHeight - 24);

				fbCont.children('.big-side.show-next')
					.css({
						'right': l - 40
					})
					.height(fbHeight)
					.children('.center, .center.hover')
						.height(fbHeight - 24);
			}

			/* Bottom UI */
			if(height < areaHeight) {
				fbNav.addClass('small').css({ top: -40 - parseInt(flipbook.css('margin-bottom').replace("px", "")) - parseInt(fbNav.css('margin-top').replace("px", "")) });
				fbNav.find('ul > li.toc > span.button-icon').css({ top: 40 });
				fbNav.find('ul > li.zoom > span.button-icon').css({ top: 38 });
				fbNav.find('ul > li.slideshow > span.button-icon, ul > li.slideshow > span.button-icon-over').css({ top: 39 });
				fbNav.find('ul > li.show-all > span.button-icon').css({ top: 40 });
			} else if (height > areaHeight) {
				fbNav.removeClass('small').css({ top: 0 });	
				fbNav.find('ul > li.toc > span.button-icon').css({ top: 25 });
				fbNav.find('ul > li.zoom > span.button-icon').css({ top: 24 });
				fbNav.find('ul > li.slideshow > span.button-icon, ul > li.slideshow > span.button-icon-over').css({ top: 25 });
				fbNav.find('ul > li.show-all > span.button-icon').css({ top: 26 });
			}
	
		});
		
		/* Flip Book Events */
		flipbook.bind('turned', function(e, page, pageObj) {
			setHashTag(flipbook);
			return false;
		});
		
		/* Global Events */
		$(window).bind('keydown', function(e) { // keyboard events
			if (e.keyCode == 37) {
				if(pageTurning)
					return;

				if(zoomed) {
					var $prev = fbCont.children('.big-side.show-previous');
					if(!$prev.is(':hidden')) {
						$prev.click();
					}
				} else {
					activeArrow = 'left';
					flipbook.trigger('mouseover');
					flipbook.turn('previous');
				}
			} else if (e.keyCode == 39) {
				if(pageTurning)
					return;

				if(zoomed) {
					var $next = fbCont.children('.big-side.show-next');
					if(!$next.is(':hidden')) {
						$next.click();
					}
				} else {
					activeArrow = 'right';
					flipbook.trigger('mouseover');
					flipbook.turn('next');
				}
			} else if (e.keyCode == 27) {
				flipbook.css({
					'visibility': 'visible',
					'pointer-events': 'all'
				});
				flipbook.stop(true).animate({opacity: 1}, 300, function(){
					hideShadows('turned', 'false', flipbook, fbCont, 'zoom');
				});  
				
				fbCont.find('div.zoomed').animate({opacity: 0}, 300, function(){
					$(this).remove();
					zoomed = false;
					fbNav.find('ul li.zoom').trigger('mouseout');
				});
				
				fbNav.css('visibility', 'visible');
				fbNav.stop(true).animate({'opacity': 1}, 300);

				fbCont.find('div.next, div.preview').stop(true).fadeIn(500);

				fbCont.find('div.zoomed-shadow-top').clearQueue();
				fbCont.find('div.zoomed-shadow-bottom').clearQueue();
				fbCont.find('div.zoomed-shadow').animate( { opacity: 0 }, 300, function(){ $(this).remove(); });
				fbCont.find('div.zoomed-shadow-bottom').animate( { opacity: 0 }, 100, function(){ $(this).remove(); });
				fbCont.find('div.zoomed-shadow-top').animate( { opacity: 0 }, 100, function(){ $(this).remove(); });
				fbCont.find('span.big-side.show-previous').fadeOut(200);
				fbCont.find('span.big-side.show-next').fadeOut(200);
			}
		}).bind('hashchange', function() { // hashchange event (unique url for each page)
			hashChange(flipbook, fbCont);	
		}).bind('touchstart', function(e) { // touch events for mobile stuff
			var t = e.originalEvent.touches;
			if (t[0]) 
				touchStart = {
						x: t[0].pageX, 
						y: t[0].pageY };
						
			touchEnd = null;
		}).bind('touchmove', function(e) {
			var t = e.originalEvent.touches, 
				pos = flipbook.offset();
				
			if (t[0].pageX>pos.left && t[0].pageY>pos.top && t[0].pageX<pos.left+flipbook.width() && t[0].pageY<pos.top+flipbook.height()) {
				if (t.length==1)
					e.preventDefault();
					
				if (t[0]) 
					touchEnd = {
						x: t[0].pageX, 
						y: t[0].pageY 
					};
			}
			
		}).bind('touchend', function(e) {
			if (window.touchStart && window.touchEnd) {
				var w = flipbook.width()/2,
					d = { 
						x: touchEnd.x-touchStart.x, 
						y: touchEnd.y-touchStart.y },
					pos = {
						x: touchStart.x-flipbook.offset().left, 
						y: touchStart.y-flipbook.offset().top };
			
				if (Math.abs(d.y)<100) {
					if(pageTurning)
						return;
					 if (d.x>100 && pos.x<w) {
					 	flipbook.turn('previous');
					 } else if (d.x<100 && pos.x>w) {
					 	flipbook.turn('next');
					 }		
				}
			}
		}).bind('start', function(e, turn) {
			if(ie) {
				flipbook.find('div.fpage object').css({ 'display' : 'none' });
				flipbook.find('div.video object').css({ 'display' : 'none' });
			} else {
				flipbook.find('div.fpage object').css({ opacity : 0 });
			}
			hideShadows('start', activeArrow, flipbook, fbCont, 'start');	
			activeCorner = true;
		}).bind('end', function(e){
			if(ie) {
				flipbook.find('div.fpage object').css({ 'display' : 'block' });
				flipbook.find('div.video object').css({ 'display' : 'block' });
			} else {
				flipbook.find('div.fpage object').css({ opacity : 1 });
			}
			hideShadows('turned', 'false', flipbook, fbCont, 'end');
			activeCorner = false;
			limiter = 0;
		});	
		
		resizeFB(firstWidth, firstHeight, flipbook, fbCont, zoomed);

		fbFirstRun(flipbook, fbCont);
	});

})();