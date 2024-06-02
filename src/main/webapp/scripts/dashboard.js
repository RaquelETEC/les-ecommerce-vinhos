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
});

function filtrar() {
    const productId = document.getElementById('produto').value;
    const startDate = document.getElementById('dataDe').value;
    const endDate = document.getElementById('dataAte').value;
    
    fetch(`/les-ecommerce-vinhos/data-analysis?action=salesData&productId=${productId}&startDate=${startDate}&endDate=${endDate}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const labels = data.map(item => item.date);
            const volumes = data.map(item => item.volume);
            renderChart(labels, volumes);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

let myChart = null; // Declare a variável para armazenar a referência do gráfico

function renderChart(labels, data) {
    // Destruir o gráfico anterior se ele existir
    if (myChart !== null) {
        myChart.destroy();
    }
    
    const ctx = document.getElementById('salesChart').getContext('2d');
    myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Volume de Vendas',
                data: data,
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
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

// Função para renderizar o gráfico de pizza
function renderPieChart(data) {
    const ctx = document.getElementById('salesPieChart').getContext('2d');
    const labels = data.map(item => item.productName);
    const volumes = data.map(item => item.volume);

    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                label: 'Volume de Vendas por Produto',
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
                }
            }
        }
    });
}

