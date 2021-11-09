Function find-TestCases([string]$MergeRequestTitle)
{
    $TargetScenarios = @()
    $MergeRequestTitle -Split " " | ForEach {
        if ($_ -Match [regex]'[a-z]+((\d)|([A-Z0-9][a-z0-9]+))+([A-Z])?') {
            $TargetScenarios = $TargetScenarios + $_
        }
    }
    return ($TargetScenarios | ForEach-Object { "--tests *.$_" }) -Replace '\\', '.'
}