import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/teachersInfo',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/teachersInfo/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/teachersInfo',
    method: 'put',
    data
  })
}

export function getTeacherCourseMessage(data) {
  return request({
    url: 'api/teachersInfo/getTeacherCourseMessage',
    method: 'get',
    data
  })
}

export function getTeacherCourseToSeeStudent(data) {
  // console.log('data', data)
  return request({
    url: 'api/teachersInfo/getTeacherCourseToSeeStudent',
    method: 'get',
    params: data
  })
}
export function getTeachersInfoByUserName(data) {
  // console.log('data', data)
  return request({
    url: 'api/teachersInfo/getTeachersInfoByUserName',
    method: 'get',
    data
  })
}
export function updateTeacherInfo(data) {
  return request({
    url: 'api/teachersInfo/updateTeacherInfo',
    method: 'post',
    params: data
  })
}

export function InsertTeacherInfo(data) {
  return request({
    url: 'api/teachersInfo/InsertTeacherInfo',
    method: 'post',
    params: data
  })
}
export default { add, edit, del, getTeacherCourseMessage, getTeacherCourseToSeeStudent, getTeachersInfoByUserName, updateTeacherInfo, InsertTeacherInfo }
