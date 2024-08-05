declare module '@vue/runtime-core' {
  export interface GlobalComponents {
    // 基础组件
    ['up-icon']: typeof import('./comps/icon')['Icon']
    ['up-image']: typeof import('./comps/image')['Image']
    ['up-button']: typeof import('./comps/button')['Button']
    ['up-text']: typeof import('./comps/text')['Text']
    ['up-row']: typeof import('./comps/row')['Row']
    ['up-col']: typeof import('./comps/col')['Col']
    ['up-cell']: typeof import('./comps/cell')['Cell']
    ['up-cell-group']: typeof import('./comps/cellGroup')['CellGroup']
    ['up-badge']: typeof import('./comps/badge')['Badge']
    ['up-tag']: typeof import('./comps/tag')['Tag']
    ['up-loading-icon']: typeof import('./comps/loadingIcon')['LoadingIcon']
    ['up-loading-page']: typeof import('./comps/loadingPage')['LoadingPage']

    // 表单组件
    ['up-form']: typeof import('./comps/form')['Form']
    ['up-form-item']: typeof import('./comps/formItem')['FormItem']
    ['up-calendar']: typeof import('./comps/calendar')['Calendar']
    ['up-keyboard']: typeof import('./comps/keyboard')['Keyboard']
    ['up-picker']: typeof import('./comps/picker')['Picker']
    ['up-datetime-picker']: typeof import('./comps/datetimePicker')['DatetimePicker']
    ['up-rate']: typeof import('./comps/rate')['Rate']
    ['up-search']: typeof import('./comps/search')['Search']
    ['up-number-box']: typeof import('./comps/numberBox')['NumberBox']
    ['up-upload']: typeof import('./comps/upload')['Upload']
    ['up-code']: typeof import('./comps/code')['Code']
    ['up-input']: typeof import('./comps/input')['Input']
    ['up-textarea']: typeof import('./comps/textarea')['Textarea']
    ['up-checkbox']: typeof import('./comps/checkbox')['Checkbox']
    ['up-checkbox-group']: typeof import('./comps/checkboxGroup')['CheckboxGroup']
    ['up-radio']: typeof import('./comps/radio')['Radio']
    ['up-radio-group']: typeof import('./comps/radioGroup')['RadioGroup']
    ['up-switch']: typeof import('./comps/switch')['Switch']
    ['up-slider']: typeof import('./comps/slider')['Slider']
    ['up-album']: typeof import('./comps/album')['Album']

    // 数据组件
    ['up-list']: typeof import('./comps/list')['List']
    ['up-list-item']: typeof import('./comps/listItem')['ListItem']
    ['up-line-progress']: typeof import('./comps/lineProgress')['LineProgress']
    ['up-count-down']: typeof import('./comps/countDown')['CountDown']
    ['up-count-to']: typeof import('./comps/countTo')['CountTo']

    // 反馈组件
    ['up-tooltip']: typeof import('./comps/tooltip')['Tooltip']
    ['up-action-sheet']: typeof import('./comps/actionSheet')['ActionSheet']
    ['up-alert']: typeof import('./comps/alert')['Alert']
    ['up-toast']: typeof import('./comps/toast')['Toast']
    ['up-notice-bar']: typeof import('./comps/noticeBar')['NoticeBar']
    ['up-notify']: typeof import('./comps/notify')['Notify']
    ['up-swipe-action']: typeof import('./comps/swipeAction')['SwipeAction']
    ['up-swipe-action-item']: typeof import('./comps/swipeActionItem')['SwipeActionItem']
    ['up-collapse']: typeof import('./comps/collapse')['Collapse']
    ['up-collapse-item']: typeof import('./comps/collapseItem')['CollapseItem']
    ['up-popup']: typeof import('./comps/popup')['Popup']
    ['up-modal']: typeof import('./comps/modal')['Modal']

    // 布局组件
    ['up-scroll-list']: typeof import('./comps/scrollList')['ScrollList']
    ['up-line']: typeof import('./comps/line')['Line']
    ['up-overlay']: typeof import('./comps/overlay')['Overlay']
    ['up-no-network']: typeof import('./comps/noNetwork')['NoNetwork']
    ['up-grid']: typeof import('./comps/grid')['Grid']
    ['up-grid-item']: typeof import('./comps/gridItem')['GridItem']
    ['up-swiper']: typeof import('./comps/swiper')['Swiper']
    ['up-skeleton']: typeof import('./comps/skeleton')['Skeleton']
    ['up-sticky']: typeof import('./comps/sticky')['Sticky']
    ['up-divider']: typeof import('./comps/divider')['Divider']

    // 导航组件
    ['up-tabbar']: typeof import('./comps/tabbar')['Tabbar']
    ['up-tabbar-item']: typeof import('./comps/tabbarItem')['TabbarItem']
    ['up-back-top']: typeof import('./comps/backTop')['BackTop']
    ['up-navbar']: typeof import('./comps/navbar')['Navbar']
    ['up-tabs']: typeof import('./comps/tabs')['Tabs']
    ['up-subsection']: typeof import('./comps/subsection')['Subsection']
    ['up-index-list']: typeof import('./comps/indexList')['IndexList']
    ['up-index-item']: typeof import('./comps/indexItem')['IndexItem']
    ['up-index-anchor']: typeof import('./comps/indexAnchor')['IndexAnchor']
    ['up-steps']: typeof import('./comps/steps')['Steps']
    ['up-steps-item']: typeof import('./comps/stepsItem')['StepsItem']
    ['up-empty']: typeof import('./comps/empty')['Empty']

    // 其他组件
    ['up-parse']: typeof import('./comps/parse')['Parse']
    ['up-code-input']: typeof import('./comps/codeInput')['CodeInput']
    ['up-load-more']: typeof import('./comps/loadMore')['LoadMore']
    ['up-read-more']: typeof import('./comps/readMore')['ReadMore']
    ['up-gap']: typeof import('./comps/gap')['Gap']
    ['up-avatar']: typeof import('./comps/avatar')['Avatar']
    ['up-avatar-group']: typeof import('./comps/avatarGroup')['AvatarGroup']
    ['up-link']: typeof import('./comps/link')['Link']
    ['up-transition']: typeof import('./comps/transition')['Transition']
  }
}

export {}
