<!-- Sending value to Android -->
        function showAndroidToast(toast) {
            jsInterface.showToast(toast);
        }

        <!-- Getting value from Android -->
        function showVersion(msg) {
            var myVar = jsInterface.getAndroidVersion();
            document.getElementById("version").innerHTML = msg + " You are running API Version " + myVar;
        }

<!-- handel scrolling issue -->
/* this func. version will be called from android side*/
/*function getElementPositionById(elementId) {
    var r = document.getElementById(elementId).getBoundingClientRect()
    var position = r.top + r.height
    *//*return ''+position+''*//*
    jsInterface.getInputPosition(position)
}*/

/* this func. version will be evaluated by android code*/
function getElementPositionById() {
    var elementId = document.activeElement.id;
    var r = document.getElementById(elementId).getBoundingClientRect()
    var position = r.top + r.height
    return position
}