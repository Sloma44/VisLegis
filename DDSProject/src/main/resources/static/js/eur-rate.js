const input = document.querySelector("input");
const button = document.querySelector(".btn-submitEur");
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
		eurRate.textContent = "Błędna data";
	}
}

button.addEventListener("click", getResult);
