import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/gradeInfo',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/gradeInfo/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/gradeInfo',
    method: 'put',
    data
  })
}
export function getTeacherCourse(data) {
  return request({
    url: 'api/gradeInfo/getTeacherCourse',
    method: 'get',
    data
  })
}

export function InsertStudentGrade(data) {
  return request({
    url: 'api/gradeInfo/InsertStudentGrade',
    method: 'post',
    params: data
  })
}

export function getCourseGrade(data) {
  return request({
    url: 'api/gradeInfo/getCourseGrade',
    method: 'get',
    params: data
  })
}

export function updateStudentGrade(data) {
  return request({
    url: 'api/gradeInfo/updateStudentGrade',
    method: 'post',
    params: data
  })
}
export function getCourseGradeByStudent(data) {
  return request({
    url: 'api/gradeInfo/getCourseGradeByStudent',
    method: 'get',
    data
  })
}
export default { add, edit, del, getTeacherCourse, InsertStudentGrade, getCourseGrade, updateStudentGrade, getCourseGradeByStudent }
