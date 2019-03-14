<#-- @ftlvariable name="announcement" type="pl.edu.wat.backend.dto.AnnouncementDto" -->
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div>
    <div>
        <h3>${announcement.type}: ${announcement.title}</h3>
        <div>
            Województwo: <b>${announcement.province}</b>, okres obowiązywania: ${announcement.timeInterval}
            <#if announcement.districts?has_content>
            <div>
                Obowiązuje dla powiatów: <#list announcement.districts as district>${district}<#sep>, </#list>
            </div>
            </#if>

        </div>
        <p>${announcement.description}</p>
    </div>
</div>
</body>
</html>
