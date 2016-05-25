var eventListenerDirectives = angular.module(
	'x.eventListener-module.directives', []);

eventListenerDirectives.directive("customOnblur", ngBlurFunc);
eventListenerDirectives.directive("customOnfocus", ngFocusFunc);

function ngBlurFunc() {
    return {
	restrict : "A",
	link : function(scope, elem, attrs) {
	    elem.bind('blur', function() {
		scope.$apply(attrs.customOnblur);
	    });
	}
    }
};

function ngFocusFunc() {
    return {
	restrict : "A",
	link : function(scope, elem, attrs) {
	    elem.bind('focus', function() {
		scope.$apply(attrs.customOnFocus);
	    });
	}
    }
}