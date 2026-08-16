$ErrorActionPreference = "Stop"

$project = "D:\SAAS\ESTELYA\backend"
$port = 8080

Write-Host "=== ESTELYA Backend ==="

$connection = Get-NetTCPConnection `
    -LocalPort $port `
    -State Listen `
    -ErrorAction SilentlyContinue

if ($connection) {

    $pidUsingPort = $connection.OwningProcess

    $process = Get-Process -Id $pidUsingPort -ErrorAction SilentlyContinue

    if ($process -and $process.ProcessName -eq "java") {

        Write-Host "Encerrando instancia anterior do Java (PID $pidUsingPort)..."

        Stop-Process `
            -Id $pidUsingPort `
            -Force

        Start-Sleep -Seconds 2

    } else {

        Write-Host "ATENCAO: A porta $port esta sendo usada por outro processo."
        Write-Host "PID: $pidUsingPort"
        exit 1
    }
}

Set-Location $project

if (-not $env:DB_PASSWORD) {

    $secure = Read-Host "Senha do estelya_app" -AsSecureString

    $ptr = [Runtime.InteropServices.Marshal]::SecureStringToBSTR($secure)

    try {
        $env:DB_PASSWORD =
            [Runtime.InteropServices.Marshal]::PtrToStringBSTR($ptr)
    }
    finally {
        [Runtime.InteropServices.Marshal]::ZeroFreeBSTR($ptr)
    }
}

Write-Host "Iniciando Estelya..."
Write-Host "http://localhost:8080"

.\mvnw.cmd spring-boot:run