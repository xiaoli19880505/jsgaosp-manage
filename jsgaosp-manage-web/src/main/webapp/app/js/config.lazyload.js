// lazyload config

angular.module('app')
    /**
   * jQuery plugin config use ui-jq directive , config the js and css files that required
   * key: function name of the jQuery plugin
   * value: array of the css js file located
   */
  .constant('JQ_CONFIG', {
      easyPieChart:   ['../assets/jquery/charts/easypiechart/jquery.easy-pie-chart.js'],
      sparkline:      ['../assets/jquery/charts/sparkline/jquery.sparkline.min.js'],
      plot:           ['../assets/jquery/charts/flot/jquery.flot.min.js', 
                          '../assets/jquery/charts/flot/jquery.flot.resize.js',
                          '../assets/jquery/charts/flot/jquery.flot.tooltip.min.js',
                          '../assets/jquery/charts/flot/jquery.flot.spline.js',
                          '../assets/jquery/charts/flot/jquery.flot.orderBars.js',
                          '../assets/jquery/charts/flot/jquery.flot.pie.min.js'],
      slimScroll:     ['../assets/jquery/slimscroll/jquery.slimscroll.min.js'],
      sortable:       ['../assets/jquery/sortable/jquery.sortable.js'],
      nestable:       ['../assets/jquery/nestable/jquery.nestable.js',
                          '../assets/jquery/nestable/nestable.css'],
      filestyle:      ['../assets/jquery/file/bootstrap-filestyle.min.js'],
      slider:         ['../assets/jquery/slider/bootstrap-slider.js',
                          '../assets/jquery/slider/slider.css'],
      chosen:         ['../assets/jquery/chosen/chosen.jquery.min.js',
                          '../assets/jquery/chosen/chosen.css'],
      TouchSpin:      ['../assets/jquery/spinner/jquery.bootstrap-touchspin.min.js',
                          '../assets/jquery/spinner/jquery.bootstrap-touchspin.css'],
      wysiwyg:        ['../assets/jquery/wysiwyg/bootstrap-wysiwyg.js',
                          '../assets/jquery/wysiwyg/jquery.hotkeys.js'],
      dataTable:      ['../assets/jquery/datatables/jquery.dataTables.min.js',
                          '../assets/jquery/datatables/dataTables.bootstrap.js',
                          '../assets/jquery/datatables/dataTables.bootstrap.css'],
      vectorMap:      ['../assets/jquery/jvectormap/jquery-jvectormap.min.js', 
                          '../assets/jquery/jvectormap/jquery-jvectormap-world-mill-en.js',
                          '../assets/jquery/jvectormap/jquery-jvectormap-us-aea-en.js',
                          '../assets/jquery/jvectormap/jquery-jvectormap.css'],
      footable:       ['../assets/jquery/footable/footable.all.min.js',
                          '../assets/jquery/footable/footable.core.css']
      }
  )
  // oclazyload config
  .config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
          debug:  false,
          events: true,
          modules: [
              {
                  name: 'ngGrid',
                  files: [
                      '../assets/modules/ng-grid/ng-grid.min.js',
                      '../assets/modules/ng-grid/ng-grid.min.css',
                      '../assets/modules/ng-grid/theme.css'
                  ]
              },
              {
                  name: 'ui.select',
                  files: [
                      '../assets/modules/angular-ui-select/select.min.js',
                      '../assets/modules/angular-ui-select/select.min.css'
                  ]
              },
              {
                  name:'angularFileUpload',
                  files: [
                    '../assets/modules/angular-file-upload/angular-file-upload.min.js'
                  ]
              },
              {
                  name:'ui.calendar',
                  files: ['../assets/modules/angular-ui-calendar/calendar.js']
              },
              {
                  name: 'ngImgCrop',
                  files: [
                      '../assets/modules/ngImgCrop/ng-img-crop.js',
                      '../assets/modules/ngImgCrop/ng-img-crop.css'
                  ]
              },
              {
                  name: 'angularBootstrapNavTree',
                  files: [
                      '../assets/modules/angular-bootstrap-nav-tree/abn_tree_directive.js',
                      '../assets/modules/angular-bootstrap-nav-tree/abn_tree.css'
                  ]
              },
              {
                  name: 'angularBootstrapNavTree2',
                  files: [
                      '../assets/modules/angular-bootstrap-nav-tree/abn2_tree_directive.js',
                      '../assets/modules/angular-bootstrap-nav-tree/abn_tree.css'
                  ]
              },
              {
                  name: 'toaster',
                  files: [
                      '../assets/modules/angularjs-toaster/toaster.js',
                      '../assets/modules/angularjs-toaster/toaster.css'
                  ]
              },
              {
                  name: 'textAngular',
                  files: [
                      '../assets/modules/textAngular/textAngular-sanitize.min.js',
                      '../assets/modules/textAngular/textAngular.min.js'
                  ]
              },
              {
                  name: 'vr.directives.slider',
                  files: [
                      '../assets/modules/angular-slider/angular-slider.min.js',
                      '../assets/modules/angular-slider/angular-slider.css'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular',
                  files: [
                      '../assets/modules/videogular/videogular.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.controls',
                  files: [
                      '../assets/modules/videogular/plugins/controls.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.buffering',
                  files: [
                      '../assets/modules/videogular/plugins/buffering.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.overlayplay',
                  files: [
                      '../assets/modules/videogular/plugins/overlay-play.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.poster',
                  files: [
                      '../assets/modules/videogular/plugins/poster.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.imaads',
                  files: [
                      '../assets/modules/videogular/plugins/ima-ads.min.js'
                  ]
              }
          ]
      });
  }])
;