import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/courseInfo',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/courseInfo/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/courseInfo',
    method: 'put',
    data
  })
}
export function getCoruseTeacher(data) {
  return request({
    url: 'api/courseInfo/getCoruseTeacher',
    method: 'get',
    params: data
  })
}

export default { add, edit, del, getCoruseTeacher }
