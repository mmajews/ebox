ebox.directive('hoverable', function ($rootScope) {
    return {
        restrict: 'A',
        link: function (scope, elem, attr) {
            var listener = $rootScope.$on('onScroll', function (ev,data) {

                var cursorOffset = $('#cursor').offset();

                var el = $(elem[0]);
                var elemOffset = el.offset();

                if(elemOffset.top < cursorOffset.top && cursorOffset.top < elemOffset.top + el.outerHeight() &&
                    elemOffset.left < cursorOffset.left && cursorOffset.left < elemOffset.left + el.outerWidth()){
                    el.addClass('hovered');
                }else{
                    el.removeClass('hovered');
                }
            });

            scope.$on('$destroy', function () {
                listener();
            });
        }
    }
});