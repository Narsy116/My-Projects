/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var recruiter = function () {
    var self = this;

    self.showMessage = function () {
        var msg = document.getElementById('handleMessage').value;
        if (msg != "") {
            alert(msg);
        }
    }
};
var recruiter = new recruiter();