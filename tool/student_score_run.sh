#!/bin/bash 

function usage() 
{
    echo " Usage : "
    echo "   bash student-core-run.sh deploy"
    echo "   bash student-core-run.sh insert name subject score "
    echo "   bash student-core-run.sh update name subject score "
    echo "   bash student-core-run.sh select name "
    echo "   bash student-core-run.sh remove name "
    echo " "
    echo " "
    echo "   examples : "
    echo "          bash student-core-run.sh deploy "
    echo "          bash student-core-run.sh insert Lucy English 100 "
    echo "          bash student-core-run.sh update Lucy English 60 "
    echo "          bash student-core-run.sh select Lucy "
    echo "          bash student-core-run.sh remove Lucy "
    exit 0
}

    case $1 in
    deploy)
            [ $# -lt 1 ] && { usage; }
            ;;
    insert)
            [ $# -lt 4 ] && { usage; }
            ;;
    update)
            [ $# -lt 4 ] && { usage; }
            ;;
    remove)
            [ $# -lt 2 ] && { usage; }
            ;;
    select)
            [ $# -lt 2 ] && { usage; }
            ;;
    *)
        usage
            ;;
    esac

    java -cp 'apps/*:conf/:lib/*' org.bcos.student.client.StudentScoreClient $@

