function resizeFrame(id, minHeight, minWidth) {
	f = document.getElementById(id);
	f.style.height = 0;
	if (minHeight == null) minHeight = 0;
	if (minWidth == null) minWidth = 0;
	if (minHeight < f.contentWindow.document.body.scrollHeight+10) f.style.height = (f.contentWindow.document.body.scrollHeight+10) + "px";
	else f.style.height = minHeight + "px";
	//if (minWidth < f.contentWindow.document.body.scrollWidth+10) f.style.width = (f.contentWindow.document.body.scrollWidth+10) + "px";
	//else f.style.height = minHeight;
}
