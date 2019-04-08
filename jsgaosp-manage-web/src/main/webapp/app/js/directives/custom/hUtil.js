/**
 * 控件指令帮助类
 *
 *
 *
 */
angular.module('app').service('hUtil', function ($http, $q, $sanitize, $interpolate, $interval, $timeout) {
    var baseHeight = 10;
    var baseCss = 'view-block';

    var intervals = [];
    return {

        intervalMgmt: {
            register: function (inter) {
                intervals.push(inter);
            },
            cancelAll: function () {
                for (var int = 0; int < intervals.length; int++) {
                    $interval.cancel(intervals[int]);
                }
            }
        },

        adjuestSize: function (size) {
            return baseHeight * size[1];
        },
        
        viewBlock: function (willCompiledHTML, size) {
            var html = [];
            var css = baseCss + ' col-md-' + size[0];
            var height = baseHeight * size[1];
            html.push('<div class="' + css + '" ');
            html.push('style="height:' + height + 'px;"');
            html.push('>');
            html.push(willCompiledHTML);
            html.push('</div>');
            return html.join('');
        },
        
        willCompiledHTML: function (widget) {
            /**
             * 拼接指令格式HTML
             */
            var html = widget.html, js = widget.js, config = widget.config, compileHtml = '', data = {};
            if (config && config.exps) {
                for (var key in config.exps) {
                    var cfg = config.exps[key];
                    data[cfg.name] = $sanitize(cfg.value);
                }
            }
            
            var htmlTmp = html;
            var jsTmp = js;

            var tmp = $(htmlTmp);
            
            var tmpNode = document.createElement('div');
            $(tmpNode).append(tmp);
            compileHtml = $(tmpNode).prop('innerHTML');
            $(tmpNode).remove();
            return compileHtml;
        },
        toJSON: function (src) {
            // 将实体转回为HTML
            function unescape(str) {
                var elem = document.createElement('div');
                elem.innerHTML = str;
                return elem.innerHTML;
            }

            return (new Function("return " + unescape(src)))();
        }
        
    }
});