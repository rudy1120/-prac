var PageScrollIndicator = PageScrollIndicator || {};

PageScrollIndicator.createProgressBar = function(progressBarLocation, contentToTrack, progressBarHeight, progressBarColor) {

  // Create the container div
  var $progressContElement = $("<div id='progress-cont'></div>");

  // Create the progress bar itself
  var $progressBarElement = $("<div id='progress-bar'></div>");
  $progressBarElement.css("width", "0%");
  if(progressBarHeight!=null) {
	  $progressContElement.css("height", progressBarHeight);
  }
  if(progressBarColor!=null) {
	  $progressBarElement.css("background-color", progressBarColor);
  }

  $progressContElement.append($progressBarElement);

  var $locationObject = $(progressBarLocation);
  $locationObject.prepend($progressContElement);

  // Event handler that updates the width of the progress bar based
  // on how far the contentToTrack elemt has been scrolled
  $(window).scroll(function(e){
    var pageHeight = $(window).height();
    var $container = $(contentToTrack);

    // Adjusted height
    var adjustedHeight = $container.innerHeight() - pageHeight;
    var progress = (($(window).scrollTop() / adjustedHeight) * 100);
    $progressBarElement.css("width", progress + "%");
  });
}
