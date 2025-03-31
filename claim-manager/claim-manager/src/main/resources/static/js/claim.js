const renderData = document.getElementById('render-data');
const searchButton = document.getElementById('search-button');
const pagination = document.getElementById('pagination');
let currentPage;
let totalPages;
const pageSize = 3;

function render(page = 0){
    const codeClaim = document.getElementById('search-ma-yeu-cau').value;
    const fromDate = document.getElementById('from-date').value;
    const toDate = document.getElementById('to-date').value;
    const statusClaim = document.getElementById('trang-thai-yeu-cau').value;

    let api = `http://localhost:8080/api/claim?page=${page}&size=${pageSize}`;
    if(codeClaim){
        api += `&claimCode=${codeClaim}`
    }
    if (fromDate){
        api += `&fromDate=${fromDate}`
    }
    if (toDate){
        api += `&toDate=${toDate}`
    }
    if (statusClaim){
        api += `&codeStatus=${statusClaim}`
    }

    fetch(api).then((res) => res.json()).then(data => {
        console.log('response: ', data);
        totalPages = data.totalPage;
        currentPage = data.pageIndex;
        const dataClaim = data.data;
        console.log(dataClaim);
        let claimList = '';
        for(let data of dataClaim){
            claimList += `
                    <tr>
                                  <td><input type="checkbox" class="recordCheckbox"></td>
                                  <td><strong>${data.code}</strong></td>
                                  <td>${data.customerName}</td>
                                  <td>${data.nameProduct}</td>
                                  <td>${data.claimDate}</td>
                                  <td>${data.coverageProduct}</td>
                                  <td><span class="badge bg-label-info me-1">${data.statusName}</span></td>
                    </tr>
                `
        }
        renderData.innerHTML = claimList;
        renderPagination();
    })
}

function renderPagination() {
    let paginationHtml = '';
    paginationHtml += `<li class="page-item ${currentPage === 0 ? 'disabled' : ''}">
        <a class="page-link" href="#" onclick="changePage(${currentPage - 1})">&laquo;</a>
    </li>`;

    for (let i = 0; i < totalPages; i++) {
        paginationHtml += `<li class="page-item ${i === currentPage ? 'active' : ''}">
            <a class="page-link" href="#" onclick="changePage(${i})">${i + 1}</a>
        </li>`;
    }
    paginationHtml += `<li class="page-item ${currentPage === totalPages - 1 ? 'disabled' : ''}">
        <a class="page-link" href="#" onclick="changePage(${currentPage + 1})">&raquo;</a>
    </li>`;

    pagination.innerHTML = paginationHtml;
}

function changePage(page) {
    if (page >= 0 && page < totalPages) {
        render(page);
    }
}

searchButton.addEventListener('click', function () {
    render(0);
});

render();

// document.addEventListener('DOMContentLoaded', function () {
//     // Lấy tất cả các thẻ th có nội dung là "Mã yêu cầu bồi thường"
//     const thElements = document.querySelectorAll('th');
//
//     thElements.forEach(th => {
//         if (th.textContent.trim() === 'Mã yêu cầu bồi thường') {
//             // Lấy tất cả các td trong cùng cột với th này
//             const thIndex = Array.from(th.parentNode.children).indexOf(th);
//             const tdElements = document.querySelectorAll('tbody tr td:nth-child(' + (thIndex + 1) + ')');
//
//             tdElements.forEach(td => {
//                 // Bọc nội dung của td trong thẻ a
//                 const a = document.createElement('a');
//                 a.href = 'detail-claim.html';
//                 a.textContent = td.textContent;
//                 td.textContent = '';
//                 td.appendChild(a);
//             });
//         }
//     });
// });
//
// document.addEventListener('DOMContentLoaded', function () {
//     const searchMaYeuCau = document.getElementById('search-ma-yeu-cau');
//     const fromDate = document.getElementById('from-date');
//     const toDate = document.getElementById('to-date');
//     const trangThaiYeuCau = document.getElementById('trang-thai-yeu-cau');
//     const searchButton = document.getElementById('search-button');
//
//     // Hàm lọc dữ liệu
//     function filterTable() {
//         const maYeuCauValue = searchMaYeuCau.value.toLowerCase();
//         const fromDateValue = fromDate.value;
//         const toDateValue = toDate.value;
//         const trangThaiValue = trangThaiYeuCau.value;
//
//         const rows = document.querySelectorAll('tbody tr');
//         rows.forEach(row => {
//             const maYeuCau = row.cells[1].textContent.toLowerCase();
//             const ngayGuiYeuCau = row.cells[4].textContent;
//             const trangThai = row.cells[6].textContent;
//
//             const showRow =
//                 (maYeuCau.includes(maYeuCauValue)) &&
//                 (!fromDateValue || new Date(ngayGuiYeuCau) >= new Date(fromDateValue)) &&
//                 (!toDateValue || new Date(ngayGuiYeuCau) <= new Date(toDateValue)) &&
//                 (!trangThaiValue || trangThai === trangThaiValue);
//
//             row.style.display = showRow ? '' : 'none';
//         });
//     }
//
//     // Thêm sự kiện lắng nghe nút tìm kiếm
//     searchButton.addEventListener('click', filterTable);
// });