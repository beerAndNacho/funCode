function getNumbeOfCases(numbers) {
	// 중복을 막기 위해서
	const result = new Set();

	// 재귀 함수를 통해 만든다.
	const temp = (currFix, eachArr) => {
		for (let i = 0; i < eachArr.length; i++) {
			const tempEachArr = [...eachArr];
			const tempCurrFixVal = tempEachArr.splice(i, 1)[0];
			const tempCurrFix = currFix + tempCurrFixVal;
			result.add(Number(tempCurrFix));
			if (tempEachArr.length > 0) temp(tempCurrFix, tempEachArr);
		}
	};

	// 시작
	for (let i = 0; i < numbers.length; i++) {
		let target = numbers[i];
		result.add(Number(target));

		const eachArr = [...numbers];
		eachArr.splice(i, 1);

		temp(target, eachArr);
	}

	return new Array(...result);
}

console.log(getNumbeOfCases(["0", "1", "2"]));