/**
 * Created by konradmarzec on 19.03.2016.
 */
'use strict';

ebox.directive('gtaGame', function (SocketService, $rootScope, $state) {
    return {
        restrict: 'E',
        templateUrl: 'javascripts/app/gta/gta.html',
        link: function($scope, element) {
            var game = new GTA.Game(element, SocketService.socket());

            var listener = $rootScope.$on('back', function () {
                $state.go('home');
            });

            $scope.$on('$destroy', function () {
                listener();
            });
        }
    }
});