function validateCheckbox()
{
    var checkboxs=document.getElementsByName("ids");
    var okay=false;
    for(var i=0,l=checkboxs.length;i<l;i++)
    {
        if(checkboxs[i].checked)
        {
            okay=true;
            break;
        }
    }
    if(!okay) alert("Please check a checkbox");
    else {
        return confirm("Are you sure you want to delete?");
    }
}