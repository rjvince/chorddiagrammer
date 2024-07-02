<#-- @ftlvariable name="" type="com.rjvince.ChordDiagram" -->
<?xml version="1.0" encoding="UTF-8" standalone="no"?><!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN"
        "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
<svg width="100%" height="100%" viewBox="0 0 621 810" version="1.1" xmlns="http://www.w3.org/2000/svg"
     xmlns:xlink="http://www.w3.org/1999/xlink" xml:space="preserve"
     style="fill-rule:evenodd;clip-rule:evenodd;stroke-linejoin:round;stroke-miterlimit:2;"><g
            transform="matrix(0.24,0,0,0.24,-770.788,-2158.36)">
        <g id="Master" transform="matrix(1,0,0,1,3211.62,8993.17)">
            <rect x="0" y="0" width="2587.5" height="3375" style="fill:none;"/>
            <clipPath id="_clip1">
                <rect x="0" y="0" width="2587.5" height="3375"/>
            </clipPath>
            <g transform="matrix(1,0,0,0.965627,0,-4108.56)">
                <rect x="-94.122" y="4152.08" width="2774.61" height="3700.6" style="fill:#fff;"/>
            </g>
            <g id="Grid" transform="matrix(0.823104,0,0,0.823104,218.692,-3035.62)">
                <path d="M2132.75,7056.88l-1675,0l0,-2425l1675,0l0,2425Zm-1123.92,-25l0,-461.483l-526.083,0l0,461.483l526.083,0Zm20.834,-461.483l0,461.483l526.083,0l0,-461.483l-526.083,0Zm1078.08,0l-527,0l0,461.483l527,0l0,-461.483Zm0,-475l-527,0l0,450l527,0l0,-450Zm-1098.92,450l0,-450l-526.083,0l0,450l526.083,0Zm20.834,-450l0,450l526.083,0l0,-450l-526.083,0Zm1078.08,-478l-527,0l0,453l527,0l0,-453Zm-1098.92,453l0,-453l-526.083,0l0,453l526.083,0Zm20.834,-453l0,453l526.083,0l0,-453l-526.083,0Zm1078.08,-480l-527,0l0,455l527,0l0,-455Zm-1098.92,455l0,-455l-526.083,0l0,455l526.083,0Zm20.834,-455l0,455l526.083,0l0,-455l-526.083,0Zm1078.08,-480.517l-527,0l0,455.517l527,0l0,-455.517Zm-1625,0l0,455.517l526.083,0l0,-455.517l-526.083,0Zm546.917,0l0,455.517l526.083,0l0,-455.517l-526.083,0Z"/>
            </g>
            <g id="Fingers" transform="matrix(0.823104,0,0,0.823104,236.594,169.856)">
                <#assign gpos=fingers[0] cpos=fingers[1] epos=fingers[2] apos=fingers[3]>
                <#if gpos == 1>
                    <g id="G1" transform="matrix(0.821747,0,0,0.821747,-173.687,179.492)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif gpos == 2>
                    <g id="G2" transform="matrix(0.821747,0,0,0.821747,-173.687,656.47)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif gpos == 3>
                    <g id="G3" transform="matrix(0.821747,0,0,0.821747,-173.687,1133.45)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif gpos == 4>
                    <g id="G4" transform="matrix(0.821747,0,0,0.821747,-173.687,1610.43)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif gpos == 5>
                    <g id="G5" transform="matrix(0.821747,0,0,0.821747,-173.687,2087.4)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                </#if>
                <#if cpos == 1>
                    <g id="C1" transform="matrix(0.821747,0,0,0.821747,375.313,179.492)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif cpos == 2>
                    <g id="C2" transform="matrix(0.821747,0,0,0.821747,375.313,656.47)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif cpos == 3>
                    <g id="C3" transform="matrix(0.821747,0,0,0.821747,375.313,1133.45)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif cpos == 4>
                    <g id="C4" transform="matrix(0.821747,0,0,0.821747,375.313,1610.43)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif cpos == 5>
                    <g id="C5" transform="matrix(0.821747,0,0,0.821747,375.313,2087.4)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                </#if>
                <#if epos == 1>
                    <g id="E1" transform="matrix(0.821747,0,0,0.821747,924.313,179.492)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif epos == 2>
                    <g id="E2" transform="matrix(0.821747,0,0,0.821747,924.313,656.47)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif epos == 3>
                    <g id="E3" transform="matrix(0.821747,0,0,0.821747,924.313,1133.45)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif epos == 4>
                    <g id="E4" transform="matrix(0.821747,0,0,0.821747,924.313,1610.43)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif epos == 5>
                    <g id="E5" transform="matrix(0.821747,0,0,0.821747,924.313,2087.4)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                </#if>
                <#if apos == 1>
                    <g id="A1" transform="matrix(0.821747,0,0,0.821747,1473.31,179.492)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif apos == 2>
                    <g id="A2" transform="matrix(0.821747,0,0,0.821747,1473.31,656.47)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif apos == 3>
                    <g id="A3" transform="matrix(0.821747,0,0,0.821747,1473.31,1133.45)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif apos == 4>
                    <g id="A4" transform="matrix(0.821747,0,0,0.821747,1473.31,1610.43)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                <#elseif apos == 5>
                    <g id="A5" transform="matrix(0.821747,0,0,0.821747,1473.31,2087.4)">
                        <ellipse cx="758.977" cy="990" rx="240.023" ry="240"/>
                    </g>
                </#if>
            </g>
            <#if (startFret > 0)>
                    <text id="fret-start" x="2160px" y="1085px"
                          style="font-family:'Alata-Regular', 'Alata';font-size:300px;">${startFret}</text>
            <#else>
                <g id="nut" transform="matrix(1.0236,0,0,1.14148,-46.5392,-5119.14)">
                    <rect x="627.203" y="5067.91" width="1346.91" height="108.545"/>
                </g>
            </#if>
            <text id="chord-name" x="1294" y="540px" text-anchor="middle" style="font-family:'Alata-Regular', 'Alata';font-size:400px;">${displayName}</text>
            <#if !suppressNoteNames>
            <text id="_1-note" x="600px" y="3090px" text-anchor="middle" style="font-family:'Alata-Regular', 'Alata';font-size:300px;">${noteNames[0]}</text>
            <text id="_2-note" x="1060px" y="3090px" text-anchor="middle" style="font-family:'Alata-Regular', 'Alata';font-size:300px;">${noteNames[1]}</text>
            <text id="_3-note" x="1510px" y="3090px"  text-anchor="middle" style="font-family:'Alata-Regular', 'Alata';font-size:300px;">${noteNames[2]}</text>
            <text id="_4-note" x="1970px" y="3090px" text-anchor="middle" style="font-family:'Alata-Regular', 'Alata';font-size:300px;">${noteNames[3]}</text>
            </#if>
        </g>
    </g></svg>
