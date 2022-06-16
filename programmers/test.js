// 메뉴 리뉴얼
const orders =["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]
const course = [2,3,4]
for(const a of orders) {
    const mapa = course.map(a=>[a]);
    course.push('mapa',mapa)
    console.log(course);
}
