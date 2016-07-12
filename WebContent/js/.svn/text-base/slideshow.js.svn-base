/* Slideshow script
*  This script display a series of images fading in & out between them.
*/

$(function(){
    $('.fadein img:gt(0)').hide();
    setInterval(function(){
      $('.fadein :eq(0)').fadeOut()
         .next().fadeIn()
         .end().appendTo('.fadein');}, 
      10000);
});