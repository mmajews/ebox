/**
 * Created by konradmarzec on 19.03.2016.
 */
'use strict';

ebox.directive('gtaGame', function () {
    return {
        restrict: 'E',
        templateUrl: 'javascripts/app/gta/gta.html',
        controller: function($scope) {
           //$scope.start = function start(){
            console.log('test');


                var game = new GTA.Game();
            //}
        }
    }
});