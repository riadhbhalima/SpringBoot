function deleteClient(id) {
    swal({
        title: "Êtes-vous sûr ?",
        text: "Une fois supprimé, vous ne pourrez plus récupérer ce client !",
        icon: "warning",
        buttons: {
            cancel: "Annuler",
            confirm: {
                text: "Oui, supprimer",
                value: true,
                visible: true,
                className: "btn-danger",
                closeModal: false
            }
        },
        dangerMode: true,
    })
    .then((willDelete) => {
        if (willDelete) {
            // Montrer un indicateur de chargement
            swal({
                title: "Suppression en cours...",
                text: "Veuillez patienter",
                icon: "info",
                buttons: false,
                closeOnClickOutside: false,
                closeOnEsc: false
            });
            
            $.ajax({
                url: "/clients/delete-ajax",
                type: "POST",
                data: { 
                    'id': id,
                    '_csrf': $('meta[name="_csrf"]').attr('content') // Pour CSRF si activé
                },
                success: function(response) {
                    // Supprimer la ligne du tableau
                    $('tr[id="' + id + '"]').remove();
                    
                    swal({
                        title: "Supprimé !",
                        text: "Le client a été supprimé avec succès.",
                        icon: "success",
                        timer: 2000,
                        buttons: false
                    });
                },
                error: function(xhr, status, error) {
                    swal({
                        title: "Erreur !",
                        text: "Échec de la suppression : " + (xhr.responseJSON?.message || error),
                        icon: "error"
                    });
                }
            });
        } else {
            swal({
                title: "Annulé",
                text: "Le client est en sécurité !",
                icon: "info",
                timer: 1500,
                buttons: false
            });
        }
    });
}