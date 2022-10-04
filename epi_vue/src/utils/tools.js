import {ElMessage} from "element-plus";

export default function messageBox(msg, type) {
    ElMessage({
        showClose: false,
        message: msg,
        type: type,
        grouping: true
    })
}


export function throttle(func, wait, mustRun) {
    let timeout, startTime = new Date()
    return function () {
        const context = this,
            args = arguments,
            curTime = new Date()
        clearTimeout(timeout)
        if (curTime - startTime >= mustRun) {
            func.apply(context, args)
            startTime = curTime
        } else {
            timeout = setTimeout(func, wait)
        }
    }
}

export function debounce(func, wait, immediate) {
    let timeout
    return function () {
        const context = this, args = arguments;
        const later = function () {
            timeout = null;
            if (!immediate) func.apply(context, args)
        }
        const callNow = immediate && !timeout
        clearTimeout(timeout)
        timeout = setTimeout(later, wait)
        if (callNow) func.apply(context, args)
    }
}