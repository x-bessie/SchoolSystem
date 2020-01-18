import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/studentInfo',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/studentInfo/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/studentInfo',
    method: 'put',
    data
  })
}

export function getStudentInfoByName(data) {
  return request({
    url: 'api/studentInfo/getStudentInfoByName',
    method: 'get',
    data
  })
}

export default { add, edit, del, getStudentInfoByName }