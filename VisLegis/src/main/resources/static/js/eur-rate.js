const input = document.querySelector(".date-input");
const button = document.querySelector(".btn-submitEurRate");
const eurRate = document.querySelector(".eurRate");

const API_LINK = "http://api.nbp.pl/api/exchangerates/rates/a/eur/";


const getResultLink = () => {
	return API_LINK + input.value;
};

async function getResult() {
	try {
		const res = await fetch(getResultLink());
		const data = await res.json();
		
		eurRate.textContent = data.rates[0].mid;

	} catch {
		eurRate.textContent = API_LINK + input.value;
	}
}

button.addEventListener("click", getResult);
