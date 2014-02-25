$(".jscroll").waypoint "infinite",
  container: "auto"
  items: ".blog-list"
  more: ".pagination>a"
  offset: "bottom-in-view"
  loadingClass: "loading"
  onBeforePageLoad: $.noop
  onAfterPageLoad: $.noop

