var droppableListenerDirectives = angular.module(
        'x.droppableListener-module.directives', []);

droppableListenerDirectives.directive("droppableToogle", toogleFuncTR);
droppableListenerDirectives.directive("droppableToogleSection",
        toogleFuncSection);

droppableListenerDirectives.directive("ngScopeElement", function() {
  var directiveDefinitionObject = {

    restrict: "A",

    compile: function compile(tElement, tAttrs, transclude) {
      return {
        pre: function preLink(scope, iElement, iAttrs, controller) {
          scope[iAttrs.ngScopeElement] = iElement;
        }
      };
    }
  };

  return directiveDefinitionObject;
});

function toogleFuncTR() {
  return {
    restrict: "A",
    template: '<i ng-scope-element="icon" class="bli bli-chevron-right" ng-click="toogleNearestDrop()"></i>',
    link: function(scope, elem, attrs) {
      scope.toogleNearestDrop = function() {
        scope.icon.toggleClass("bli-chevron-down");
        scope.icon.toggleClass("bli-chevron-right");
        if (elem.parent().parent().next().hasClass("droppable")) {
          elem.parent().parent().next().toggleClass("hideDiv");
        }
      }
    }
  }
};
function toogleFuncSection() {
  return {
    restrict: "A",
    scope : {
      for: "@for"
    },
    template: '<i ng-scope-element="icon" class="bli bli-chevron-down" ng-data="for" ng-click="toogleNearestDrop1()"></i>',
    link: function(scope, elem, attrs) {

      scope.toogleNearestDrop1 = function() {
        scope.icon.toggleClass("bli-chevron-down");
        scope.icon.toggleClass("bli-chevron-right");
        var vicElem = angular.element(document.querySelector('#' + scope.for));
        vicElem.toggleClass("hideDiv");
      }
    }
  }
};