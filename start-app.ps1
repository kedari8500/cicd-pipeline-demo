$java = Join-Path $env:JAVA_HOME 'bin\javaw.exe'
$cmd = '"' + $java + '" -jar "C:\deploy\myapp\myapp.jar"'
Invoke-CimMethod -ClassName Win32_Process -MethodName Create -Arguments @{CommandLine=$cmd} | Out-Null
