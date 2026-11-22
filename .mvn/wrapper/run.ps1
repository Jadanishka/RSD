$ErrorActionPreference = "Stop"
$props = Get-Content (Join-Path $PSScriptRoot "maven-wrapper.properties")
$line = $props | Where-Object { $_.StartsWith("distributionUrl=") } | Select-Object -First 1
$url = $line.Substring(16)
$fileName = [System.IO.Path]::GetFileName($url)
$name = $fileName -replace "-bin.zip$", ""
$cache = Join-Path $HOME ".m2\wrapper\$name"
$mvn = Join-Path $cache "$name\bin\mvn.cmd"
if (-not (Test-Path $mvn)) {
    New-Item -ItemType Directory -Path $cache -Force | Out-Null
    $archive = Join-Path $cache $fileName
    Invoke-WebRequest -Uri $url -OutFile $archive
    Expand-Archive -Path $archive -DestinationPath $cache -Force
    Remove-Item $archive
}
& $mvn @args
exit $LASTEXITCODE
