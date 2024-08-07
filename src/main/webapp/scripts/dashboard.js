document.addEventListener('DOMContentLoaded', function() {
	fetch('/les-ecommerce-vinhos/data-analysis?action=listProducts')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			const produtoSelect = document.getElementById('produto');
			data.forEach(produto => {
				const option = document.createElement('option');
				option.value = produto.id;
				option.text = produto.desc;
				produtoSelect.add(option);
			});
		})
		.catch(error => {
			console.error('There was a problem with the fetch operation:', error);
		});
		
		filtrar();
});

function filtrar() {
	const productId = document.getElementById('produto').value;
	const startDate = document.getElementById('dataDe').value;
	const endDate = document.getElementById('dataAte').value;

	let url = `/les-ecommerce-vinhos/data-analysis?action=salesData&startDate=${startDate}&endDate=${endDate}`;

	if (productId !== "all") {
		url += `&productId=${productId}`;
	}

	fetch(url)
		.then(response => response.json())
		.then(data => {
			const labels = data.map(item => item.date);
			const descriçãoProd = data.map(item => item.productName);
			const volumes = data.map(item => item.volume);
			
			renderChart(data);
			//renderPieChart(data)
			renderBarChart(data);
			renderTotalSales(data);
		});
		
}
let myChart = null;
let myChartPie = null;

function renderChart(data) {
    // Destruir o gráfico anterior se ele existir
    if (myChart !== null) {
        myChart.destroy();
    }

    const ctx = document.getElementById('salesChart').getContext('2d');

    // Obter todas as datas únicas
    const uniqueDates = Array.from(new Set(data.map(item => item.date)));

    // Obter todos os produtos únicos
    const uniqueProducts = Array.from(new Set(data.map(item => item.productName)));

	let datasets ;

    datasets = uniqueProducts.map(product => {
        // Calcular o volume para cada data para o produto atual
        const volumes = uniqueDates.map(date => {
            const volume = data
                .filter(item => item.date === date && item.productName === product)
                .reduce((total, item) => total + item.volume, 0);
            return volume;
        });

        return {
            label: product,
            data: volumes,
            borderColor: getRandomColor(), // Função para gerar uma cor aleatória
            borderWidth: 1,
            fill: false
        };
    });

    myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: uniqueDates,
            datasets: datasets
        },
        options: {
            scales: {
                x: {
                    type: 'category',
                },
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function getRandomColor() {
    // Cores predefinidas em HSL (Matiz, Saturação, Luminosidade)
    const colors = [
        'hsl(0, 100%, 50%)',   // Vermelho
        'hsl(45, 100%, 50%)',  // Laranja
        'hsl(90, 100%, 50%)',  // Amarelo
        'hsl(135, 100%, 50%)', // Verde lima
        'hsl(180, 100%, 50%)', // Verde
        'hsl(225, 100%, 50%)', // Ciano
        'hsl(270, 100%, 50%)', // Azul
        'hsl(315, 100%, 50%)'  // Magenta
    ];

    // Escolhe uma cor aleatória do array de cores
    return colors[Math.floor(Math.random() * colors.length)];
}


// Função para renderizar o gráfico de pizza
function renderPieChart(data) {
	
	  // Destruir o gráfico anterior se ele existir
    if (myChartPie !== null) {
        myChartPie.destroy();
    }

    const ctx = document.getElementById('salesPieChart').getContext('2d');
    
    // Filtrar produtos únicos
    const uniqueProducts = [];
    data.forEach(item => {
        if (!uniqueProducts.includes(item.productName)) {
            uniqueProducts.push(item.productName);
        }
    });

    // Calcular o total de volume
    const totalVolume = data.reduce((total, item) => total + item.volume, 0);

    // Calcular o percentual de vendas para cada produto
    const volumes = uniqueProducts.map(productName => {
        const volume = data
            .filter(item => item.productName === productName)
            .reduce((total, item) => total + item.volume, 0);
        return volume;
    });

    myChartPie = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: uniqueProducts,
            datasets: [{
                label: 'Vendas por Produto',
                data: volumes,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        	},
	      options: {
	        scales: {
	            y: {
	                beginAtZero: true
	            },
	            
	        },
	        plugins: {
	            legend: {
	                position: 'right' // Posiciona a legenda à direita
	            }
	        }
	    }
	});
}

let myChartBar = null;

function renderBarChart(data) {
    // Destruir o gráfico anterior se ele existir
    if (myChartBar !== null) {
        myChartBar.destroy();
    }

    const ctx = document.getElementById('salesBarChart').getContext('2d');

    // Filtrar produtos únicos
    const uniqueProducts = [];
    data.forEach(item => {
        if (!uniqueProducts.includes(item.productName)) {
            uniqueProducts.push(item.productName);
        }
    });

    // Calcular o total de volume
    const totalVolume = data.reduce((total, item) => total + item.volume, 0);

    // Calcular o percentual de vendas para cada produto
    const volumes = uniqueProducts.map(productName => {
        const volume = data
            .filter(item => item.productName === productName)
            .reduce((total, item) => total + item.volume, 0);
        return volume;
    });

    myChartBar = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: uniqueProducts,
            datasets: [{
                label: 'Vendas por Produto',
                data: volumes,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                },
                x: {
                	display: false // Hide X axis labels
            	}
            },
		    plugins: {
			    legend: {
			      display: false
			    }
		  	}
        }
    });
}


function renderTotalSales(data) {
    const totalSales = data.reduce((total, item) => total + (item.volume * item.valor), 0);
    const totalSalesQuant = data.reduce((total, item) => total + item.volume, 0);

    const totalElement = document.getElementById('totalSales');
    const totalElementQuant = document.getElementById('totalSalesQuant');

    totalElement.textContent = `R$ ${totalSales.toFixed(2)}`;
    totalElementQuant.textContent = ` ${totalSalesQuant} un`;

  
}