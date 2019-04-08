angular.module('myaccordion', ['ui.bootstrap.collapse'])
    .constant('accordionConfig', {
        closeOthers: true
    })
    .controller('AccordionController', ['$scope', '$attrs', 'accordionConfig', function ($scope, $attrs, accordionConfig) {
        this.groups = [];
        this.closeOthers = function (openGroup) {
            var closeOthers = angular.isDefined($attrs.closeOthers) ? $scope.$eval($attrs.closeOthers) : accordionConfig.closeOthers;
            if (closeOthers) {
                angular.forEach(this.groups, function (group) {
                    if (group !== openGroup) {
                        group.isOpen = false;
                    }
                });
            }
        };
        this.addGroup = function (groupScope) {
            var that = this;
            this.groups.push(groupScope);

            groupScope.$on('$destroy', function (event) {
                that.removeGroup(groupScope);
            });
        };
        this.removeGroup = function (group) {
            var index = this.groups.indexOf(group);
            if (index !== -1) {
                this.groups.splice(index, 1);
            }
        };

    }])
    .directive('accordionCount', function () {
        return {
            restrict: 'EA',
            controller: 'AccordionController',
            transclude: true,
            replace: false,
            template: "<div class=\"panel-group\" ng-transclude></div>"
        };
    })
    .directive('accordionCountGroup', function () {
        return {
            require: '^accordionCount',
            restrict: 'EA',
            transclude: true,
            replace: true,
            template: "<div class=\"panel panel-default\">\n" +
            "  <div class=\"panel-heading\" ng-click=\"toggleOpen()\" style='cursor: pointer;background-color: #ffffff'>\n" +
            "    <h4 class=\"panel-title\">\n" +
            "      <a href class=\"accordion-toggle\" style=\"display:inline;\" accordion-count-transclude=\"heading\"><span ng-class=\"{'text-muted': isDisabled}\" title=\"{{heading}}\">{{heading|long}}</span></a>\n" +
            "       <span class=\"pull-right badge\" style=\"  background-color: #edf1f2;color: #58666e;\">{{count}}</span>" +
            "    </h4>\n" +
            "  </div>\n" +
            "  <div class=\"panel-collapse\" collapse=\"!isOpen\">\n" +
            "	  <div class=\"panel-body\" ng-transclude></div>\n" +
            "  </div>\n" +
            "</div>\n",
            scope: {
                heading: '@',
                count: '@',
                isOpen: '=?',
                isDisabled: '=?'
            },
            controller: function () {
                this.setHeading = function (element) {
                    this.heading = element;
                };
            },
            link: function (scope, element, attrs, accordionCtrl) {
                accordionCtrl.addGroup(scope);

                scope.$watch('isOpen', function (value) {
                    if (value) {
                        accordionCtrl.closeOthers(scope);
                    }
                });

                scope.toggleOpen = function () {
                    if (!scope.isDisabled) {
                        scope.isOpen = !scope.isOpen;
                    }
                };
            }
        };
    })
    .directive('accordionCountHeading', function () {
        return {
            restrict: 'EA',
            transclude: true,
            template: '',
            replace: true,
            require: '^accordionCountGroup',
            link: function (scope, element, attr, accordionGroupCtrl, transclude) {
                accordionGroupCtrl.setHeading(transclude(scope, function () {
                }));
            }
        };
    })
    .directive('accordionCountTransclude', function () {
        return {
            require: '^accordionCountGroup',
            link: function (scope, element, attr, controller) {
                scope.$watch(function () {
                    return controller[attr.accordionCountTransclude];
                }, function (heading) {
                    if (heading) {
                        element.html('');
                        element.append(heading);
                    }
                });
            }
        };
    }).filter('long', function () {
        return function (input) {
            if(input.length>12) {
                var result = input.substr(0, 12);
                return result + "...";
            }else{
                return input;
            }
        }
    });