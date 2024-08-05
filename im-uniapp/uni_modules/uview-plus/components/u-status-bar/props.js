import { defineMixin } from '../../libs/vue'
import defProps from '../../libs/config/props.js'
export const props = defineMixin({
    props: {
        bgColor: {
            type: String,
            default: () => defProps.statusBar.bgColor
        }
    }
})
