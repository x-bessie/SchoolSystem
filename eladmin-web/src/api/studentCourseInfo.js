import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/studentCourseInfo',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/studentCourseInfo/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/studentCourseInfo',
    method: 'put',
    data
  })
}

export function getStudentCourseMessage(data) {
  return request({
    url: 'api/studentCourseInfo/getStudentCourseMessage',
    method: 'get',
    data
  })
}

export default { add, edit, del, getStudentCourseMessage }
