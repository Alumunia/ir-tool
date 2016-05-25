var saveDirectives = angular.module('x.save-module.directives', []);

saveDirectives.directive('blurToCurrency',
        function ($filter) {
            return {
                scope: {
                    amount: '='
                },
                link: function (scope, el, attrs) {
                    el.val($filter('currency')(scope.amount, ''));

                    el.bind('focus', function () {
                        el.val(scope.amount);
                    });

                    el.bind('input', function () {
                        scope.amount = el.val();
                        scope.$apply();
                    });

                    el.bind('blur', function () {
                        el.val($filter('currency')(scope.amount, ''));
                    });
                }
            }
        });

saveDirectives.directive('onlyDigits', function () {
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function (scope, element, attr, ctrl) {
            function inputValue(val) {
                if (val) {
                    var digits = val.replace(/[^0-9.]/g, '');

                    if (digits !== val) {
                        ctrl.$setViewValue(digits);
                        ctrl.$render();
                    }
                    return parseFloat(digits);
                }
                return undefined;
            }
            ctrl.$parsers.push(inputValue);
        }
    };
});

/*
 * digits only input text directive
 * ex: <input type="number" ng-model="scope.model" only-numbers>
 */
saveDirectives.directive('onlyNumbers', function () {
    return function (scope, element, attrs) {

        var keyCode = [8, 9, 37, 39, 38, 40, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 110];
        element.bind("keydown", function (event) {
            console.log($.inArray(event.which, keyCode));
            if ($.inArray(event.which, keyCode) == -1) {
                scope.$apply(function () {
                    scope.$eval(attrs.onlyNum);
                    event.preventDefault();
                });
                event.preventDefault();
            }

        });
    };
})

saveDirectives.directive('onlyDigitsWithoutPunct', function () {
    return {
        require: 'ngModel',
        restrict: 'A',
        link: function (scope, element, attr, ctrl) {
            function inputValue(val) {
                if (val) {
                    var digits = val.replace(/[^0-9]/g, '');

                    if (digits !== val) {
                        ctrl.$setViewValue(digits);
                        ctrl.$render();
                    }
                    return parseFloat(digits);
                }
                return undefined;
            }
            ctrl.$parsers.push(inputValue);
        }
    };
});


saveDirectives.directive("fileModel", ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };


}]);

saveDirectives.directive('draggable', function() {
  return {
	// A = attribute, E = Element, C = Class and M = HTML Comment
	restrict:'A',
	//The link function is responsible for registering DOM listeners as well as updating the DOM.
	link: function(scope, element, attrs) {
	  var node ={
          label:scope.node.label,
          value:scope.node.value,
          type:scope.node.type
      }
	  element.draggable({
		revert:"invalid",
          helper: "clone",
          cursor: "move",
		start: function(e,ui){
		  console.log(node);
		  $(element).addClass('position-absolute-promotion');

		},
		stop: function(e,ui){
		  $(element).removeClass('position-absolute-promotion');
		  $(element).css("position", "relative");
		}
	  });
	}
  };
});

saveDirectives.directive('droppable', function($compile) {
  return {
	restrict: 'A',
	link: function(scope,element,attrs){
	  var node = scope.node;
	  //This makes an element Droppable
	  element.droppable({
		drop:function(event,ui) {
		  var dragNode = angular.element(ui.draggable).data('node'),
				  dropEl = angular.element(this);

            var type= angular.element(this).data('field');
            console.log(type);

            if(type==="include") {
                scope.addCategoryInclude(dragNode);
            }
            else if(type==="exclude")
            {
                scope.addCategoryExclude(dragNode);
            }

		  $(this).css("border-color", "#bbb");

		  scope.$apply();
		},
		over: function()
		{
		  $(this).css("border-color", "#4cff00");
		},
		out:function(){
		  $(this).css("border-color", "");
		}
	  });
	}
  };
});

saveDirectives.directive('currencyRp', function ($filter) {
    return {
        require: 'ngModel',
        link: function(elem, $scope, attrs, ngModel){
            ngModel.$formatters.push(function(val){
                return $filter('currency')(val, 'Rp ')
            });
            ngModel.$parsers.push(function(val){
                return val.replace(/^\$/, '')
            });
        }
    }
})
