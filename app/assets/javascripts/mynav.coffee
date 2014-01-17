$(".navbar-nav li").click (e)->
  $(".navbar-nav li.active").removeClass "active"
  $this=$(this)
  $this.addClass "active" until $this.hasClass "active"
  e.preventDefault