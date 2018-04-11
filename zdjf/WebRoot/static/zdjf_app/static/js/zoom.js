(function(){
    var _w,_zoom,_hd, _orientationChange,_doc = document,__style = _doc.getElementById("_zoom");
    __style || (_hd = _doc.getElementsByTagName("head")[0],__style=_doc.createElement("style"),_hd.appendChild(_style));
    _orientationChange = function(){
        _w    = _doc.documentElement.clientWidth || _doc.body.clientWidth;
        _zoom = _w / 640;
        __style.innerHTML = ".zoom {zoom:" + _zoom + ";-webkit-text-size-adjust:auto !important;text-size-adjust:auto !important;}";
        // var rem = _w / 10;
        // window.document.documentElement.style.fontSize = rem + 'px';
    };
    __style.setAttribute("zoom",_zoom);
    _orientationChange();
    window.addEventListener("resize",_orientationChange,false);
})();
//rem布局
(function(win) {
    var doc = win.document;
    var docEl = doc.documentElement;
    var tid;

    function refreshRem() {
        var width = docEl.getBoundingClientRect().width;
        if (width > 640) { // 最大宽度
            width = 640;
        }
        var rem = width / 10;
        docEl.style.fontSize = rem + 'px';
    }

    win.addEventListener('resize', function() {
        clearTimeout(tid);
        tid = setTimeout(refreshRem, 300);
    }, false);
    win.addEventListener('pageshow', function(e) {
        if (e.persisted) {
            clearTimeout(tid);
            tid = setTimeout(refreshRem, 300);
        }
    }, false);

    refreshRem();

})(window);
// (function(doc, win) {
//     var docEl = doc.documentElement;
//     var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
//     var recalc = function() {
//         var clientWidth = docEl.clientWidth;
//         if(!clientWidth) return;
//         if(clientWidth >= 640) {
//             docEl.style.fontSize = '100px';
//         } else {
//             docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
//         }
//     };
//     if(!doc.addEventListener) return;
//     win.addEventListener(resizeEvt, recalc, false);
//     doc.addEventListener('DOMContentLoaded', recalc, false);
// })(document, window);