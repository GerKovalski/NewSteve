window.onload = function() {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function (e) {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            //console.log(httpRequest.responseText);
            document.getElementById("ct_body").innerHTML = httpRequest.responseText;
        }
    }
    httpRequest.open("GET", 'http://localhost:6703/api/criminals', true);
    httpRequest.send();
}