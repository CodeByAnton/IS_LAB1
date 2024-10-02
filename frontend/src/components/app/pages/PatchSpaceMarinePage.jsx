import React, { useState } from 'react';
import ChaptersTable from "../user/ChaptersTable";
import SpaceMarineForm from "../user/SpaceMarineForm";
import CoordinatesTable from "../user/CoordinatesTable";
import styles from '../../../css/PatchSpaceMarinePage.module.css'; // Предполагается, что у вас есть соответствующий CSS-модуль

const PatchSpaceMarinePage = ({ initialData, close }) => {
    const [refresh, setRefresh] = useState(false);

    const handleRefresh = () => {
        setRefresh(!refresh);
    };

    return (
        <div className={styles.overlay}>
                <ChaptersTable refresh={refresh} />
                <SpaceMarineForm initialData={initialData} onSuccess={() => { handleRefresh(); close(); }} />
                <CoordinatesTable refresh={refresh} />
                <button className={styles.cancelButton} onClick={close}>Cancel</button>
        </div>
    );
};

export default PatchSpaceMarinePage;